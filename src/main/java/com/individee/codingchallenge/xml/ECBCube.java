package com.individee.codingchallenge.xml;

import javax.xml.bind.annotation.XmlElement;

public class ECBCube {

    private ECBCube2 cube;

    @XmlElement(name = "Cube", namespace = "http://www.ecb.int/vocabulary/2002-08-01/eurofxref")
    public ECBCube2 getCube() {
        return cube;
    }

    public void setCube(ECBCube2 cube) {
        this.cube = cube;
    }
}
