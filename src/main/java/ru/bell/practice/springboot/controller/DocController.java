package ru.bell.practice.springboot.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bell.practice.springboot.service.docServise.DocService;
import ru.bell.practice.springboot.view.docView.DocView;

import java.util.List;

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
    public List<DocView> list() {
        return docService.list();
    }
}
