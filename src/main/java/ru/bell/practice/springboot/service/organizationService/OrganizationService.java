package ru.bell.practice.springboot.service.organizationService;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.bell.practice.springboot.model.Organization;
import ru.bell.practice.springboot.view.organizationView.OrganizationFilterView;
import ru.bell.practice.springboot.view.organizationView.OrganizationSaveView;
import ru.bell.practice.springboot.view.organizationView.OrganizationUpdateView;
import ru.bell.practice.springboot.view.organizationView.OrganizationView;

import java.util.List;

@Validated
public interface OrganizationService {
    List<OrganizationView> listByName(OrganizationFilterView organizationFilterView);
    OrganizationView organizationByID(Long id);
    void update(OrganizationUpdateView organizationUpdateView);
    void save(OrganizationSaveView organizationSaveView);
}
