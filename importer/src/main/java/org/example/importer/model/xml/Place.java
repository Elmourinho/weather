package org.example.importer.model.xml;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "place")
@Getter
public class Place {

    @XmlElement
    private String name;

    @XmlElement
    private String phenomenon;

    @XmlElement
    private int tempmin;

    @XmlElement
    private int tempmax;
}
