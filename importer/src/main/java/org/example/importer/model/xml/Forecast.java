package org.example.importer.model.xml;

import lombok.Getter;
import org.example.importer.adapter.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Getter
public class Forecast {

    @XmlAttribute
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate date;

    @XmlElement
    private Day day;

    @XmlElement
    private Night night;
}
