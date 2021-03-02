package com.individee.codingchallenge.config.validator;


import com.individee.codingchallenge.config.ECBProperties;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;

public class ECBPropertiesValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ECBProperties.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ECBProperties properties = (ECBProperties) target;

        // validate xmlOutputPath property
        Path xmlOutputPath = properties.getXmlOutputPath();
        if(xmlOutputPath != null) {
            if (!Files.exists(xmlOutputPath)) {
                errors.rejectValue("xmlOutputPath", "ecb.xml-output-path.exists");
            } else if (!Files.isDirectory(xmlOutputPath)) {
                errors.rejectValue("xmlOutputPath", "ecb.xml-output-path.isdirectory");
            } else {
                FileSystem fileSystem = FileSystems.getDefault();
                FileSystemProvider provider = fileSystem.provider();
                try {
                    provider.checkAccess(xmlOutputPath, AccessMode.READ, AccessMode.WRITE);
                } catch (AccessDeniedException e) {
                    errors.rejectValue("xmlOutputPath", "ecb.xml-output-path.rwaccess");
                } catch (IOException e) {
                    errors.rejectValue("xmlOutputPath", "file.error", e.getMessage());
                }
            }
        }
        // validate xrefURL property
        String xrefUrl = properties.getXrefUrl();
        if (xrefUrl != null) {
            try {
                //noinspection ResultOfMethodCallIgnored
                URI.create(xrefUrl);
            } catch (IllegalArgumentException e) {
                errors.rejectValue("xrefUrl", "ecb.xref-uri.invalidurl");
            }
        }
    }
}
