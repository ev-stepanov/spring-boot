package ru.bell.practice.springboot.service.officeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
 * Сервис для работы с офисами
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
        if (officeInFilterView.getOrgId() == null){
            throw new WrongRequestException("Field 'orgId' is null.");
        }
        Office office = mapperFacade.map(officeInFilterView, Office.class);

        Organization organization = organizationDao.getById(officeInFilterView.getOrgId());
        if (organization == null) {
            throw new RecordNotFoundException("Record with id = " + officeInFilterView.getOrgId() + " was not found on Organization.");
        }
        office.setOrganization(organization);

        List<Office> offices = officeDao.filter(office);
        return mapperFacade.mapAsList(offices, OfficeOutFilterView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeView getById(Long id) {
        if (id == null) {
            throw new WrongRequestException("Field 'id' is null.");
        }

        Office officeById = officeDao.getById(id);

        if (officeById == null) {
            throw new RecordNotFoundException("Record with id = " + id + " was not found on Office.");
        }

        return mapperFacade.map(officeById, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeUpdateView officeUpdateView) {
        validateToUpdateView(officeUpdateView);

        Office officeById = officeDao.getById(officeUpdateView.getId());

        officeById.setName(officeUpdateView.getName());
        officeById.setAddress(officeUpdateView.getAddress());

        if (officeUpdateView.getPhone() != null) {
            officeById.setPhone(officeUpdateView.getPhone());
        }

        if (officeUpdateView.getActive() != null) {
            officeById.setActive(officeUpdateView.getActive());
        }

        officeDao.update(officeById);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OfficeSaveView officeSaveView) {
        validateToSaveView(officeSaveView);
        Office office = mapperFacade.map(officeSaveView, Office.class);
        office.setOrganization(organizationDao.getById(officeSaveView.getOrgId()));
        officeDao.save(office);
    }

    private boolean isNameValid(String name){
        return name.matches("[a-zA-Zа-яА-Я0-9 ]{1,50}");
    }

    private boolean isAddressValid(String address){
        return address.matches("[a-zA-Zа-яА-Я0-9 ,./-]{1,255}");
    }

    private boolean isPhoneValid(String phone){
        return phone.matches("(\\+?)\\d{7,11}");
    }

    private void validateToUpdateView(OfficeUpdateView officeUpdateView){
        StringBuilder messageBuilder = new StringBuilder();

        if (officeUpdateView.getId() == null){
            messageBuilder.append("Field 'id' is null. ");
        }
        if (officeUpdateView.getName() == null || !isNameValid(officeUpdateView.getName())){
            messageBuilder.append("Field 'name' is null or invalid. ");
        }
        if (officeUpdateView.getAddress() == null || !isAddressValid(officeUpdateView.getAddress())){
            messageBuilder.append("Field 'address' is null or invalid. ");
        }
        if (officeUpdateView.getPhone() != null && !isPhoneValid(officeUpdateView.getPhone())){
            messageBuilder.append("Field 'phone' is null or invalid. ");
        }

        if (messageBuilder.length() > 0){
            throw new WrongRequestException(messageBuilder.toString().trim());
        }
        if (officeDao.getById(officeUpdateView.getId()) == null){
            throw new RecordNotFoundException("Record with id = " + officeUpdateView.getId() + " was not found in Office.");
        }
    }

    private void validateToSaveView(OfficeSaveView officeSaveView){
        StringBuilder messageBuilder = new StringBuilder();

        if (officeSaveView.getOrgId() == null) {
            messageBuilder.append("Field 'orgId' is null. ");
        }
        if (messageBuilder.length() > 0){
            throw new WrongRequestException(messageBuilder.toString().trim());
        }
        if (organizationDao.getById(officeSaveView.getOrgId()) == null){
            throw new RecordNotFoundException("Record with id = " + officeSaveView.getOrgId() + " was not found in Organization.");
        }
    }
}