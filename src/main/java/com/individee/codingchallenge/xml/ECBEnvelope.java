package com.individee.codingchallenge.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Envelope", namespace = "http://www.gesmes.org/xml/2002-08-01")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ECBEnvelope {

    private ECBCube cube;

    @XmlElement(name = "Cube", namespace = "http://www.ecb.int/vocabulary/2002-08-01/eurofxref")
    public ECBCube getCube() {
        return cube;
    }

    public void setCube(ECBCube cube) {
        this.cube = cube;
    }
}
