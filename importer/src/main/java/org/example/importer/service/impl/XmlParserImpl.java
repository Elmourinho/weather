package org.example.importer.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.importer.model.ForecastDto;
import org.example.importer.model.xml.Day;
import org.example.importer.model.xml.Forecasts;
import org.example.importer.model.xml.Place;
import org.example.importer.service.XmlParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class XmlParserImpl implements XmlParser {

    @Override
    public List<ForecastDto> parse(Forecasts forecasts) {
        var dayForecastDtoList = getDayForecasts(forecasts);
        var nightForecastDtoList = getNightForecasts(forecasts);
        return mergeForecasts(dayForecastDtoList, nightForecastDtoList);
    }

    private List<ForecastDto> getNightForecasts(Forecasts forecasts) {
        List<ForecastDto> forecastDtoList = new ArrayList<>();
        var forecastList = forecasts.getForecastList();
        forecastList.forEach(forecast -> {
            var night = forecast.getNight();
            var places = night.getPlaces();
            if (places != null) {
                places.forEach(place -> forecastDtoList.add(
                        ForecastDto.builder()
                        .date(forecast.getDate())
                        .placeName(place.getName())
                        .nightPhenomenon(place.getPhenomenon())
                        .tempMin(place.getTempmin())
                        .build()));
            }
        });
        return forecastDtoList;
    }

    private List<ForecastDto> getDayForecasts(Forecasts forecasts) {
        List<ForecastDto> forecastDtoList = new ArrayList<>();
        var forecastList = forecasts.getForecastList();
        forecastList.forEach(forecast -> {
            Day day = forecast.getDay();
            List<Place> places = day.getPlaces();
            if (places != null) {
                places.forEach(place -> forecastDtoList.add(
                        ForecastDto.builder()
                                .date(forecast.getDate())
                                .placeName(place.getName())
                                .dayPhenomenon(place.getPhenomenon())
                                .tempMax(place.getTempmax())
                                .build()));
            }
        });
        return forecastDtoList;
    }

    private List<ForecastDto> mergeForecasts(List<ForecastDto> dayForecasts, List<ForecastDto> nightForecasts) {
        Map<String, ForecastDto> nightForecastMap = nightForecasts
                .stream()
                .collect(
                        Collectors.toMap(ForecastDto::getPlaceName, dto -> dto));

        dayForecasts.forEach(dayForecast -> {
            var nightForecast = nightForecastMap.get(dayForecast.getPlaceName());
            if (nightForecast != null) {
                dayForecast.setTempMin(nightForecast.getTempMin());
                dayForecast.setNightPhenomenon(nightForecast.getNightPhenomenon());
            }
        });
        return dayForecasts;
    }

}