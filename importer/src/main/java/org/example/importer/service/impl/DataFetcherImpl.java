package org.example.importer.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.importer.model.xml.Forecasts;
import org.example.importer.service.DataFetcher;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import java.net.URI;

@Service
@Slf4j
public class DataFetcherImpl implements DataFetcher {

    private static final String XML_URL = "https://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";
    @Override
    public Forecasts fetchData() {
        try {
            var jaxbContext = JAXBContext.newInstance(org.example.importer.model.xml.Forecasts.class);
            var unmarshaller = jaxbContext.createUnmarshaller();
            return (Forecasts) unmarshaller.unmarshal(new URI(XML_URL).toURL());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
