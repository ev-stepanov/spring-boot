package ru.bell.practice.springboot.service.organizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bell.practice.springboot.dao.organizationDao.OrganizationDao;
import ru.bell.practice.springboot.model.Organization;
import ru.bell.practice.springboot.model.mapper.MapperFacade;
import ru.bell.practice.springboot.view.organizationView.OrganizationFilterView;
import ru.bell.practice.springboot.view.organizationView.OrganizationSaveView;
import ru.bell.practice.springboot.view.organizationView.OrganizationUpdateView;
import ru.bell.practice.springboot.view.organizationView.OrganizationView;

import java.util.Collections;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final MapperFacade mapperFacade;
    private final OrganizationDao organizationDao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> listByName(OrganizationFilterView organizationFilterView) {
        Organization organization = mapperFacade.map(organizationFilterView, Organization.class);
        List<Organization> organizations = organizationDao.listByName(organization);
        if (organizations.isEmpty()) {
            return Collections.emptyList();
        }
        return mapperFacade.mapAsList(organizations, OrganizationView.class);
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationView organizationByID(Long id) {
        Organization organization = organizationDao.organizationByID(id);
        return mapperFacade.map(organization, OrganizationView.class);
    }

    @Override
    @Transactional
    public void update(OrganizationUpdateView organizationUpdateView) {
        organizationDao.update(mapperFacade.map(organizationUpdateView, Organization.class));
    }

    @Override
    @Transactional
    public void save(OrganizationSaveView organizationSaveView) {
        organizationDao.save(mapperFacade.map(organizationSaveView, Organization.class));
    }
}