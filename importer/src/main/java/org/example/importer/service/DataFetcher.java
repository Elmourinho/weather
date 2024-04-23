package org.example.importer.service;

import org.example.importer.model.xml.Forecasts;

public interface DataFetcher {

    Forecasts fetchData();
}
