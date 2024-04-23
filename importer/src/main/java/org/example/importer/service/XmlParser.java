package org.example.importer.service;

import org.example.importer.model.ForecastDto;
import org.example.importer.model.xml.Forecasts;

import java.util.List;

public interface XmlParser {

    List<ForecastDto> parse(Forecasts forecasts);
}
