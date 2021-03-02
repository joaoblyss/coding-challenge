package com.individee.codingchallenge.sched;

import com.individee.codingchallenge.config.ECBProperties;
import com.individee.codingchallenge.converter.RatingsConverter;
import com.individee.codingchallenge.domain.Ratings;
import com.individee.codingchallenge.service.RatingsService;
import com.individee.codingchallenge.xml.ECBEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

@Named
public class ExchangeRatesDailyUpdater {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRatesDailyUpdater.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private ECBProperties properties;
    private RatingsService service;
    private RatingsConverter converter;

    @Inject
    public void setProperties(ECBProperties properties) {
        this.properties = properties;
    }

    @Inject
    public void setService(RatingsService service) {
        this.service = service;
    }

    @Inject
    public void setConverter(RatingsConverter converter) {
        this.converter = converter;
    }

    @PostConstruct
    void onInit() {
        updateECBRatings();
    }

    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void updateECBRatings() {
        if (this.properties.getXrefUrl() != null) {
            try {
                Path xmlFle = fetchFromUrl();
                Unmarshaller unmarshaller = JAXBContext.newInstance(ECBEnvelope.class).createUnmarshaller();
                ECBEnvelope envelope = (ECBEnvelope) unmarshaller.unmarshal(xmlFle.toFile());
                Ratings ratings = this.converter.convert(envelope);
                //noinspection ConstantConditions
                Ratings persistent = this.service.findByDate(ratings.getDate());
                if (persistent == null) {
                    this.service.save(ratings);
                    LOGGER.info("ECB ratings successfully updated");
                } else {
                    LOGGER.info("ECB ratings are up-to-date");
                }
            } catch (IOException | JAXBException e) {
                LOGGER.error("Failed while attempting to reach \"%s\"");
            }
        } else {
            LOGGER.warn("No ECB data URL was set. Skipping update");
        }
    }

    Path fetchFromUrl() throws IOException {
        LOGGER.info("fetching daily currency ratings data from the ECB");
        String xrefUri = this.properties.getXrefUrl();
        Path xmlOutputPath = this.properties.getXmlOutputPath();
        if (xmlOutputPath == null) {
            xmlOutputPath = Files.createTempDirectory("ecb-xref");
        }
        URL xrefUrl = URI.create(xrefUri).toURL();
        InputStream in = xrefUrl.openStream();
        Path xmlFile = xmlOutputPath.resolve(String.format("%s.xml", DATE_FORMAT.format(new Date())));
        Files.copy(in, xmlFile, StandardCopyOption.REPLACE_EXISTING);
        return xmlFile;
    }

}
