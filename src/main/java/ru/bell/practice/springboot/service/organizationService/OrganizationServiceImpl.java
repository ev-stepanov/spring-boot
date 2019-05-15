package ru.bell.practice.springboot.service.organizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bell.practice.springboot.dao.organizationDao.OrganizationDao;
import ru.bell.practice.springboot.exception.RecordNotFoundException;
import ru.bell.practice.springboot.model.Organization;
import ru.bell.practice.springboot.model.mapper.MapperFacade;
import ru.bell.practice.springboot.view.organizationView.*;

import java.util.List;

/**
 * Сервис для работы с организациями
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final MapperFacade mapperFacade;
    private final OrganizationDao organizationDao;

    /**
     * Конструктор
     *
     * @param organizationDao DAO слой для работы с огранизациями
     * @param mapperFacade Фасад для преобразования между моделями БД и фронта
     */
    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationOutFilterView> list(OrganizationInFilterView organizationInFilterView) {
        List<Organization> organizations = organizationDao.filter(
                organizationInFilterView.getName(), organizationInFilterView.getInn(), organizationInFilterView.getActive());

        return mapperFacade.mapAsList(organizations, OrganizationOutFilterView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationView getById(Long id) {
        Organization organization = organizationDao.getById(id);

        if (organization == null) {
            throw new RecordNotFoundException();
        }

        return mapperFacade.map(organization, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationUpdateView organizationUpdateView) {
        Organization organizationByID = organizationDao.getById(organizationUpdateView.getId());

        if (organizationByID == null) {
            throw new RecordNotFoundException();
        }

        organizationByID.setName(organizationUpdateView.getName());
        organizationByID.setFullName(organizationUpdateView.getFullName());
        organizationByID.setInn(organizationUpdateView.getInn());
        organizationByID.setKpp(organizationUpdateView.getKpp());
        organizationByID.setAddress(organizationUpdateView.getAddress());

        if (organizationUpdateView.getPhone() != null){
            organizationByID.setPhone(organizationUpdateView.getPhone());
        }

        if (organizationUpdateView.getActive() != null) {
            organizationByID.setActive(organizationUpdateView.getActive());
        }

        organizationDao.update(organizationByID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OrganizationSaveView organizationSaveView) {
        organizationDao.save(mapperFacade.map(organizationSaveView, Organization.class));
    }
}