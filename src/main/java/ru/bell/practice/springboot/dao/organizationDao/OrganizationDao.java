package ru.bell.practice.springboot.dao.organizationDao;

import ru.bell.practice.springboot.model.Organization;

import java.util.List;

public interface OrganizationDao {
    List<Organization> listByName(Organization organization);
    Organization organizationByID(Long id);
    void update(Organization organization);
    void save(Organization organization);
}
