package ru.bell.practice.springboot.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ru.bell.practice.springboot.view.officeView.OfficeFilterView;
import ru.bell.practice.springboot.view.officeView.OfficeSaveView;
import ru.bell.practice.springboot.view.officeView.OfficeUpdateView;
import ru.bell.practice.springboot.view.officeView.OfficeView;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для работы с офисами
 */
@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    @ApiOperation(value = "Get a list of all organizations by id organization", httpMethod = "POST")
    @PostMapping("/list")
    public List<OfficeView> getListOfficesByFilter (@RequestBody OfficeFilterView officeFilterView) {
        return Collections.emptyList();
    }

    @ApiOperation(value = "Get the office by id", httpMethod = "GET")
    @GetMapping("/{id}")
    public OfficeView getOfficeById (@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "Update of information about the office", httpMethod = "POST")
    @PostMapping("/update")
    public Boolean update(@RequestBody OfficeUpdateView officeUpdateView) {
        return false;
    }

    @ApiOperation(value = "Save of information about the office", httpMethod = "POST")
    @PostMapping("/save")
    public Boolean save(@RequestBody OfficeSaveView officeSaveView) {
        return false;
    }
}
