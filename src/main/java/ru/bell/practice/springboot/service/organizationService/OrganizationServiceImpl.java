package ru.bell.practice.springboot.service.organizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bell.practice.springboot.dao.organizationDao.OrganizationDao;
import ru.bell.practice.springboot.exception.RecordNotFoundException;
import ru.bell.practice.springboot.exception.WrongRequestException;
import ru.bell.practice.springboot.model.Organization;
import ru.bell.practice.springboot.model.mapper.MapperFacade;
import ru.bell.practice.springboot.view.organizationView.OrganizationFilterView;
import ru.bell.practice.springboot.view.organizationView.OrganizationSaveView;
import ru.bell.practice.springboot.view.organizationView.OrganizationUpdateView;
import ru.bell.practice.springboot.view.organizationView.OrganizationView;

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
        if (organizationFilterView.getName() == null){
            throw new WrongRequestException("Field 'name' is null.");
        }

        Organization organization = mapperFacade.map(organizationFilterView, Organization.class);
        List<Organization> organizations = organizationDao.listByName(organization);

        return mapperFacade.mapAsList(organizations, OrganizationView.class);
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationView organizationByID(Long id) {
        if (id == null) {
            throw new WrongRequestException("Field 'id' is null.");
        }

        Organization organization = organizationDao.organizationByID(id);

        if (organization == null) {
            throw new RecordNotFoundException("Record with id = " + id + " was not found on Organization");
        }

        return mapperFacade.map(organization, OrganizationView.class);
    }

    @Override
    @Transactional
    public void update(OrganizationUpdateView organizationUpdateView) {
        validateView(organizationUpdateView);

        Organization organizationByID = organizationDao.organizationByID(organizationUpdateView.getId());

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

    @Override
    @Transactional
    public void save(OrganizationSaveView organizationSaveView) {
        validateToSaveView(organizationSaveView);
        organizationDao.save(mapperFacade.map(organizationSaveView, Organization.class));
    }

    private boolean isNameValid(String name){
        return name.matches("[a-zA-Zа-яА-Я \\d*]{1,50}");
    }

    private boolean isFullNameValid(String fullName){
        return fullName.matches("[a-zA-Zа-яА-Я ]{1,255}");
    }

    private boolean isInnValid(String inn){
        return inn.matches("[0-9]{10}");
    }

    private boolean isKppValid(String kpp){
        return kpp.matches("[0-9]{10}");
    }

    private boolean isAddressValid(String address){
        return address.matches("[a-zA-Zа-яА-Я0-9 ,./-]{1,255}");
    }

    private boolean isPhoneValid(String phone){
        return phone.matches("(\\+?)\\d{7,11}");
    }

    private void validateView(OrganizationUpdateView view){
        StringBuilder messageBuilder = new StringBuilder();

        if (view.getId() == null){
            messageBuilder.append("Field 'id' is null. ");
        }

        validate(messageBuilder, view.getName(), view.getFullName(), view.getInn(), view.getKpp(), view.getAddress(), view.getPhone());

        if (organizationDao.organizationByID(view.getId()) == null){
            throw new RecordNotFoundException("Record with 'id' = " + view.getId() + " was not found on Organization.");
        }
    }

    private void validateToSaveView(OrganizationSaveView view){
        StringBuilder messageBuilder = new StringBuilder();
        validate(messageBuilder, view.getName(), view.getFullName(), view.getInn(), view.getKpp(), view.getAddress(), view.getPhone());
    }

    private void validate(StringBuilder messageBuilder, String name, String fullName, String inn, String kpp, String address, String phone) {
        if (name == null || !isNameValid(name)){
            messageBuilder.append("Field 'name' is null or invalid. ");
        }
        if (fullName == null || !isFullNameValid(fullName)){
            messageBuilder.append("Field 'fullName' is null or invalid. ");
        }
        if (inn == null || !isInnValid(inn)){
            messageBuilder.append("Field 'inn' is null or invalid. ");
        }
        if (kpp == null || !isKppValid(kpp)){
            messageBuilder.append("Field 'kpp' is null or invalid. ");
        }
        if (address == null || !isAddressValid(address)){
            messageBuilder.append("Field 'address' is null or invalid. ");
        }
        if (phone != null && !isPhoneValid(phone)){
            messageBuilder.append("Field 'phone' is invalid. ");
        }

        if (messageBuilder.length() > 0){
            throw new WrongRequestException(messageBuilder.toString().trim());
        }
    }
}