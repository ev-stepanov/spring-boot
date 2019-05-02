package ru.bell.practice.springboot.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bell.practice.springboot.service.officeService.OfficeService;
import ru.bell.practice.springboot.view.officeView.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для работы с офисами
 */
@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;

    /**
     * Конструктор
     *
     * @param officeService сервис, предоставляющий методы работы с офисами
     */
    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Возвращает отфильтрованный список офисов.
     *
     * @param officeInFilterView фильтр для списка
     * @return отфильтрованный список
     */
    @ApiOperation(value = "Get a list of all organizations by id organization", httpMethod = "POST")
    @PostMapping("/list")
    public List<OfficeOutFilterView> getListOfficesByFilter (@RequestBody OfficeInFilterView officeInFilterView) {
        return officeService.list(officeInFilterView);
    }

    /**
     * Возвращает офис с указанным id.
     *
     * @param id идентификатор офиса
     * @return офис с указанным id
     */
    @ApiOperation(value = "Get the office by id", httpMethod = "GET")
    @GetMapping("/{id:[\\d]+}")
    public OfficeView getOfficeById (@PathVariable("id") Long id) {
        return officeService.getById(id);
    }

    /**
     * Обнавляет сведения об офисе.
     *
     * @param officeUpdateView - содержит сведения об обновлении
     */
    @ApiOperation(value = "Update of information about the office", httpMethod = "POST")
    @PostMapping("/update")
    public void update(@RequestBody OfficeUpdateView officeUpdateView) {
        officeService.update(officeUpdateView);
    }

    /**
     * Сохраняет сведения об новом офисе.
     *
     * @param officeSaveView - содержит сведения о новом офис
     */
    @ApiOperation(value = "Save of information about the office", httpMethod = "POST")
    @PostMapping("/save")
    public void save(@RequestBody OfficeSaveView officeSaveView) {
        officeService.save(officeSaveView);
    }
}
