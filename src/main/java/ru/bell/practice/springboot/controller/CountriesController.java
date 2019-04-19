package ru.bell.practice.springboot.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bell.practice.springboot.service.countryService.CountryService;
import ru.bell.practice.springboot.view.countryView.CountryView;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class CountriesController {

    private final CountryService countryService;

    @Autowired
    public CountriesController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ApiOperation(value = "Get countries list", httpMethod = "GET")
    @GetMapping("/countries")
    public List<CountryView> list() {
        return countryService.list();
    }
}
