package com.individee.codingchallenge.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.nio.file.Path;

@ConfigurationProperties("ecb")
public class ECBProperties {

    private URI xrefUri;
    private Path xmlOutputPath;

    public URI getXrefUri() {
        return xrefUri;
    }

    public void setXrefUri(URI xrefUri) {
        this.xrefUri = xrefUri;
    }

    public Path getXmlOutputPath() {
        return xmlOutputPath;
    }

    public void setXmlOutputPath(Path xmlOutputPath) {
        this.xmlOutputPath = xmlOutputPath;
    }
}
