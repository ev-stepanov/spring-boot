package ru.bell.practice.springboot.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bell.practice.springboot.response.view.DataResponseView;
import ru.bell.practice.springboot.response.view.SuccessResponseView;
import ru.bell.practice.springboot.service.officeService.OfficeService;
import ru.bell.practice.springboot.view.officeView.OfficeInFilterView;
import ru.bell.practice.springboot.view.officeView.OfficeSaveView;
import ru.bell.practice.springboot.view.officeView.OfficeUpdateView;

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
    public DataResponseView getListOfficesByFilter (@RequestBody OfficeInFilterView officeInFilterView) {
        return new DataResponseView(officeService.list(officeInFilterView));
    }

    /**
     * Возвращает офис с указанным id.
     *
     * @param id идентификатор офиса
     * @return офис с указанным id
     */
    @ApiOperation(value = "Get the office by id", httpMethod = "GET")
    @GetMapping("/{id:[\\d]+}")
    public DataResponseView getOfficeById (@PathVariable("id") Long id) {
        return new DataResponseView(officeService.getById(id));
    }

    /**
     * Обнавляет сведения об офисе.
     *
     * @param officeUpdateView - содержит сведения об обновлении
     * @return успешность операции
     */
    @ApiOperation(value = "Update of information about the office", httpMethod = "POST")
    @PostMapping("/update")
    public SuccessResponseView update(@RequestBody OfficeUpdateView officeUpdateView) {
        officeService.update(officeUpdateView);
        return new SuccessResponseView(true);
    }

    /**
     * Сохраняет сведения об новом офисе.
     *
     * @param officeSaveView - содержит сведения о новом офис
     * @return успешность операции
     */
    @ApiOperation(value = "Save of information about the office", httpMethod = "POST")
    @PostMapping("/save")
    public SuccessResponseView save(@RequestBody OfficeSaveView officeSaveView) {
        officeService.save(officeSaveView);
        return new SuccessResponseView(true);
    }
}
