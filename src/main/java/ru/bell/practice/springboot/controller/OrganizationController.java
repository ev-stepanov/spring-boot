package ru.bell.practice.springboot.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ru.bell.practice.springboot.view.organizationView.OrganizationByNameView;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    @ApiOperation(value = "Get a list of all organizations by name", httpMethod = "POST")
    @PostMapping(value = "/list")
    public List<OrganizationByNameView> getListOrganizations(@RequestBody OrganizationByNameView organization) {
        return Collections.emptyList();
    }

    @ApiOperation(value = "Get the organization by id ", httpMethod = "GET")
    @GetMapping("/organizations/{id}")
    public List<OrganizationByNameView> organizations(@PathVariable("id") Long organization_id) {
        return Collections.emptyList();
    }

    @ApiOperation(value = "Update of information about the organization", httpMethod = "POST")
    @PostMapping(value = "/update")
    public Boolean update() {
        return false;
    }

    @ApiOperation(value = "Add a new organization", httpMethod = "POST")
    @PostMapping(value = "/save")
    public Boolean save() {
        return false;
    }
}