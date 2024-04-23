package org.example.importer.model.xml;

import lombok.Getter;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Getter
public class Night {

    @XmlElement
    private String text;

    @XmlElement
    private String phenomenon;

    @XmlElement(name = "place")
    private List<Place> places;
}
