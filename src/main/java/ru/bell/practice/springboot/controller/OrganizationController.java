package ru.bell.practice.springboot.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bell.practice.springboot.service.organizationService.OrganizationService;
import ru.bell.practice.springboot.view.organizationView.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для работы с организациями
 */
@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    /**
     * Конструктор
     *
     * @param organizationService - сервис для работы с организациями
     */
    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Возвращает отфильтрованный список организаций.
     *
     * @param organization - фильтр
     * @return отфильтрованный список
     */
    @ApiOperation(value = "Get a list of all organizations by name", httpMethod = "POST")
    @PostMapping(value = "/list")
    public List<OrganizationOutFilterView> getListOrganizationsByFilter(@RequestBody OrganizationInFilterView organization) {
        return organizationService.list(organization);
    }

    /**
     * Возврщает организацию с указанным ид
     * @param organization_id - идентификатор организации
     * @return организация с указанным ид
     */
    @ApiOperation(value = "Get the organization by id", httpMethod = "GET")
    @GetMapping("/{id:[\\d]+}")
    public OrganizationView getListOrganizationById(@PathVariable("id") Long organization_id) {
        return organizationService.getById(organization_id);
    }

    /**
     * Обновляет информацию об организации.
     *
     * @param organizationUpdateView - сведения обновленные сведения об организации
     * @return успешность операции
     */
    @ApiOperation(value = "Update of information about the organization", httpMethod = "POST")
    @PostMapping(value = "/update")
    public void update(@RequestBody OrganizationUpdateView organizationUpdateView) {
         organizationService.update(organizationUpdateView);
    }

    /**
     * Сохраняет данные о новой организации.
     *
     * @param organizationSaveView - сведения о новой организации
     * @return успещность операции
     */
    @ApiOperation(value = "Add a new organization", httpMethod = "POST")
    @PostMapping(value = "/save")
    public void save(@RequestBody OrganizationSaveView organizationSaveView) {
        organizationService.save(organizationSaveView);
    }
}