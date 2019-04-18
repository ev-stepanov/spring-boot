package ru.bell.practice.springboot.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bell.practice.springboot.service.docServise.DocService;
import ru.bell.practice.springboot.view.docView.DocView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class DocController {

    private final DocService docService;

    @Autowired
    public DocController(DocService docService) {
        this.docService = docService;
    }

    @ApiOperation(value = "Get docs list", httpMethod = "GET")
    @GetMapping("/docs")
    public List<DocView> list() {
        return docService.list();
    }
}
