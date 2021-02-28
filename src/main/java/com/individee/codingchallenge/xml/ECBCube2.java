package com.individee.codingchallenge.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;
import java.util.List;

public class ECBCube2 {

    private Date time;
    private List<ECBCube3> cubes;

    @XmlAttribute(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @XmlElement(name = "Cube", namespace = "http://www.ecb.int/vocabulary/2002-08-01/eurofxref")
    public List<ECBCube3> getCubes() {
        return cubes;
    }

    public void setCubes(List<ECBCube3> cubes) {
        this.cubes = cubes;
    }
}
