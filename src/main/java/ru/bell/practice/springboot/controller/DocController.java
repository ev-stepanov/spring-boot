package ru.bell.practice.springboot.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bell.practice.springboot.response.view.DataResponseView;
import ru.bell.practice.springboot.service.docServise.DocService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для работы со справочными документами (удовстверения)
 */
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class DocController {

    private final DocService docService;

    /**
     * Конструктор
     *
     * @param docService сервис, предоставляющий методы для получения справочной информации о документах
     */
    @Autowired
    public DocController(DocService docService) {
        this.docService = docService;
    }

    /**
     * Возвращает список документов, удостоверяющих личность и их кодов.
     *
     * @return список документов и их кодов
     */
    @ApiOperation(value = "Get docs list", httpMethod = "GET")
    @GetMapping("/docs")
    public DataResponseView list() {
        return new DataResponseView(docService.list());
    }
}
