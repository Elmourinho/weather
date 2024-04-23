package org.example.importer.service.impl;

import org.example.importer.model.ForecastDto;
import org.example.importer.model.xml.Day;
import org.example.importer.model.xml.Forecast;
import org.example.importer.model.xml.Forecasts;
import org.example.importer.model.xml.Night;
import org.example.importer.model.xml.Place;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class XmlParserImplTest {

    @Test
    void parse_ReturnsMergedForecasts_WhenCalled() {

        Forecasts forecasts = mock(Forecasts.class);
        Forecast forecast1 = mock(Forecast.class);
        Forecast forecast2 = mock(Forecast.class);

        Day day1 = mock(Day.class);
        Night night1 = mock(Night.class);
        Day day2 = mock(Day.class);
        Night night2 = mock(Night.class);

        Place place1 = createPlace("Harku", "Sunny", 20);
        Place place2 = createPlace("Turi", "Cloudy", 15);
        Place place3 = createPlace("London", "Clear", 10);
        Place place4 = createPlace("Barcelona", "Foggy", 5);
        Place place5 = createPlace("Rome", "Rainy", 18);
        Place place6 = createPlace("Lisbon", "Stormy", 12);
        Place place7 = createPlace("Baku", "Snowy", 5);
        Place place8 = createPlace("Warsaw", "Windy", 2);

        when(forecast1.getDay()).thenReturn(day1);
        when(forecast1.getNight()).thenReturn(night1);
        when(forecast1.getDate()).thenReturn(LocalDate.of(2024, 4, 24));

        when(forecast2.getDay()).thenReturn(day2);
        when(forecast2.getNight()).thenReturn(night2);
        when(forecast2.getDate()).thenReturn(LocalDate.of(2024, 4, 25));


        when(day1.getPlaces()).thenReturn(Arrays.asList(place1, place2));
        when(night1.getPlaces()).thenReturn(Arrays.asList(place3, place4));
        when(day2.getPlaces()).thenReturn(Arrays.asList(place5, place6));
        when(night2.getPlaces()).thenReturn(Arrays.asList(place7, place8));

        when(forecasts.getForecastList()).thenReturn(Arrays.asList(forecast1, forecast2));

        XmlParserImpl xmlParser = new XmlParserImpl();

        List<ForecastDto> result = xmlParser.parse(forecasts);

        assertEquals(4, result.size());
        assertEquals(LocalDate.of(2024, 4, 24), result.get(0).getDate());
        assertEquals("Harku", result.get(0).getPlaceName());
        assertEquals("Sunny", result.get(0).getDayPhenomenon());
        assertEquals(25, result.get(0).getTempMax());
        assertEquals(LocalDate.of(2024, 4, 24), result.get(1).getDate());
        assertEquals("Turi", result.get(1).getPlaceName());
        assertEquals("Cloudy", result.get(1).getDayPhenomenon());
        assertEquals(20, result.get(1).getTempMax());
        assertEquals(0, result.get(1).getTempMin());
    }

    private Place createPlace(String name, String phenomenon, int temperature) {
        Place place = mock(Place.class);
        when(place.getName()).thenReturn(name);
        when(place.getPhenomenon()).thenReturn(phenomenon);
        when(place.getTempmin()).thenReturn(temperature);
        when(place.getTempmax()).thenReturn(temperature + 5);
        return place;
    }

}