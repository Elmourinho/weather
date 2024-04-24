package org.example.importer.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.entity.ForecastEntity;
import org.example.common.entity.ForecastId;
import org.example.common.repository.ForecastRepository;
import org.example.importer.model.ForecastDto;
import org.example.importer.model.xml.Forecasts;
import org.example.importer.service.DataFetcher;
import org.example.importer.service.DataImporter;
import org.example.importer.service.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataImporterImpl implements DataImporter {

    private final DataFetcher dataFetcher;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ForecastRepository forecastRepository;

    @Override
    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.MINUTES)
    public void importData() {
        log.info("Fetching xml data...");
        Forecasts forecasts = dataFetcher.fetchData();
        if (forecasts == null) {
            return;
        }
        log.info("Xml data fetched!");
        List<ForecastDto> forecastDtoList = xmlParser.parse(forecasts);
        List<ForecastEntity> forecastEntityList = convertForecasts(forecastDtoList);
        forecastRepository.saveAll(forecastEntityList);
        log.info("Imported {} rows...", forecastEntityList.size());
    }

    private List<ForecastEntity> convertForecasts(List<ForecastDto> forecastDtoList) {
        return forecastDtoList
                .stream()
                .map(this::convertForecast)
                .toList();
    }

    private ForecastEntity convertForecast(ForecastDto forecastDto) {
        ForecastEntity forecast = modelMapper.map(forecastDto, ForecastEntity.class);
        ForecastId forecastId = new ForecastId(forecastDto.getDate(), forecastDto.getPlaceName());
        forecast.setId(forecastId);
        return forecast;
    }
}
