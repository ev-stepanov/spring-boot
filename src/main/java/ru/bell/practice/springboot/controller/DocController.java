package ru.bell.practice.springboot.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bell.practice.springboot.view.docView.DocView;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class DocController {

    @ApiOperation(value = "Get docs list", httpMethod = "GET")
    @PostMapping("/docs")
    public List<DocView> list() {
        return Collections.emptyList();
    }
}
