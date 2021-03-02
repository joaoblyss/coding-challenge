package com.individee.codingchallenge.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.nio.file.Path;

@Validated
@ConfigurationProperties("ecb")
public class ECBProperties {

    private String xrefUrl;
    private Path xmlOutputPath;

    public String getXrefUrl() {
        return xrefUrl;
    }

    public void setXrefUrl(String xrefUrl) {
        this.xrefUrl = xrefUrl;
    }

    public Path getXmlOutputPath() {
        return xmlOutputPath;
    }

    public void setXmlOutputPath(Path xmlOutputPath) {
        this.xmlOutputPath = xmlOutputPath;
    }
}
