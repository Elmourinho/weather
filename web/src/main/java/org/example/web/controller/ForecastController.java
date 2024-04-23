package org.example.web.controller;

import org.example.common.repository.ForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ForecastController {

    private final ForecastRepository forecastRepository;

    @Autowired
    public ForecastController(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @GetMapping("/index")
    public String showForecastList(
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {
        model.addAttribute("forecasts", forecastRepository.findByIdDate(date));
        return "index";
    }
}
