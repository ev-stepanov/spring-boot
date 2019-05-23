package ru.bell.practice.springboot.service.officeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bell.practice.springboot.dao.officeDao.OfficeDao;
import ru.bell.practice.springboot.dao.organizationDao.OrganizationDao;
import ru.bell.practice.springboot.exception.RecordNotFoundException;
import ru.bell.practice.springboot.exception.WrongRequestException;
import ru.bell.practice.springboot.model.Office;
import ru.bell.practice.springboot.model.Organization;
import ru.bell.practice.springboot.model.mapper.MapperFacade;
import ru.bell.practice.springboot.view.officeView.*;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final OrganizationDao organizationDao;
    private final OfficeDao officeDao;
    private MapperFacade mapperFacade;

    /**
     * Конструктор
     *
     * @param officeDao - DAO слой для работы с офисами
     * @param organizationDao - DAO слой для работы с огранизациями
     * @param mapperFacade - Фасад для преобразования между моделями БД и фронта
     */
    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeOutFilterView> list(OfficeInFilterView officeInFilterView) {
        List<Office> offices = officeDao.filter(officeInFilterView.getOrgId(),
                officeInFilterView.getName(), officeInFilterView.getPhone(), officeInFilterView.getActive());
        return mapperFacade.mapAsList(offices, OfficeOutFilterView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeView getById(Long id) {
        Office officeById = officeDao.getById(id);
        if (officeById == null) {
            throw new RecordNotFoundException();
        }

        return mapperFacade.map(officeById, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeUpdateView officeUpdateView) {
        Office office = officeDao.getById(officeUpdateView.getId());
        if (office == null) {
            throw new WrongRequestException();
        }
        office.setName(officeUpdateView.getName());
        office.setAddress(officeUpdateView.getAddress());
        if (officeUpdateView.getPhone() != null) {
            office.setPhone(officeUpdateView.getPhone());
        }
        if (officeUpdateView.getActive() != null) {
            office.setActive(officeUpdateView.getActive());
        }
        officeDao.update(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OfficeSaveView officeSaveView) {
        Office office = mapperFacade.map(officeSaveView, Office.class);
        Organization organization = organizationDao.getById(officeSaveView.getOrgId());
        if (organization == null) {
            throw new WrongRequestException();
        }
        office.setOrganization(organization);
        officeDao.save(office);
    }
}