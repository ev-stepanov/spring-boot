package ru.bell.practice.springboot.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bell.practice.springboot.response.view.DataResponseView;
import ru.bell.practice.springboot.service.countryService.CountryService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для работы со справочными документами (гражданствами)
 */
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class CountriesController {

    private final CountryService countryService;

    /**
     * Конструктор
     *
     * @param countryService сервис, предоставляющий методы для получения справочной информации о гражданствах
     */
    @Autowired
    public CountriesController(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Возвращает список стран и их кодов.
     *
     * @return список стран и их кодов
     */
    @ApiOperation(value = "Get countries list", httpMethod = "GET")
    @GetMapping("/countries")
    public DataResponseView list() {
        return new DataResponseView(countryService.list());
    }
}
