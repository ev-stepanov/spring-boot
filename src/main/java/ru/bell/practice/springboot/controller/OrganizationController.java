package ru.bell.practice.springboot.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bell.practice.springboot.service.organizationService.OrganizationService;
import ru.bell.practice.springboot.view.organizationView.OrganizationFilterView;
import ru.bell.practice.springboot.view.organizationView.OrganizationSaveView;
import ru.bell.practice.springboot.view.organizationView.OrganizationUpdateView;
import ru.bell.practice.springboot.view.organizationView.OrganizationView;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для работы с организациями
 */
@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "Get a list of all organizations by name", httpMethod = "POST")
    @PostMapping(value = "/list")
    public List<OrganizationView> getListOrganizationsByFilter(@RequestBody OrganizationFilterView organization) {
        return organizationService.listByName(organization);
    }

    @ApiOperation(value = "Get the organization by id", httpMethod = "GET")
    @GetMapping("/{id}")
    public OrganizationView getListOrganizationById(@PathVariable("id") Long organization_id) {
        return organizationService.organizationByID(organization_id);
    }

    @ApiOperation(value = "Update of information about the organization", httpMethod = "POST")
    @PostMapping(value = "/update")
    public void update(@RequestBody OrganizationUpdateView organizationUpdateView) {
        organizationService.update(organizationUpdateView);
    }

    @ApiOperation(value = "Add a new organization", httpMethod = "POST")
    @PostMapping(value = "/save")
    public void save(@RequestBody OrganizationSaveView organizationSaveView) {
        organizationService.save(organizationSaveView);
    }
}