package ru.bell.practice.springboot.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.bell.practice.springboot.dao.countryDao.CountryDao;
import ru.bell.practice.springboot.dao.docDao.DocDao;
import ru.bell.practice.springboot.dao.officeDao.OfficeDao;
import ru.bell.practice.springboot.dao.userDao.UserDao;
import ru.bell.practice.springboot.exception.RecordNotFoundException;
import ru.bell.practice.springboot.exception.WrongRequestException;
import ru.bell.practice.springboot.model.*;
import ru.bell.practice.springboot.model.mapper.MapperFacade;
import ru.bell.practice.springboot.view.officeView.OfficeSaveView;
import ru.bell.practice.springboot.view.userView.*;

import java.util.Date;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements  UserService {

    private final CountryDao countryDao;
    private final DocDao docDao;
    private UserDao userDao;
    private final OfficeDao officeDao;
    private MapperFacade mapperFacade;

    /**
     * Конструктор
     *
     * @param userDao      dao для пользователей
     * @param officeDao    dao для офисов
     * @param mapperFacade маппер фасад
     * @param docDao       dao для документов
     * @param countryDao   dao для гражданств
     */
    @Autowired
    public UserServiceImpl(UserDao userDao, OfficeDao officeDao, MapperFacade mapperFacade, DocDao docDao, CountryDao countryDao) {
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
        this.docDao = docDao;
        this.countryDao = countryDao;
    }


    @Override
    @Transactional(readOnly = true)
    public List<UserOutFilterView> list(UserInFilterView userInFilterView) {
        if (userInFilterView.getOfficeId() == null) {
            throw new WrongRequestException("Field 'officeId' cannot be null.");
        }

        User user = mapperFacade.map(userInFilterView, User.class);
        Office office = officeDao.getById(userInFilterView.getOfficeId());

        if (office == null) {
            throw new RecordNotFoundException("Record with id = " + userInFilterView.getOfficeId() + " was not found on Office");
        }

        user.setOffice(office);

        if (userInFilterView.getDocCode() != null) {
            user.setDocUser(new DocUser());
            user.getDocUser().setDocType(docDao.getByCode(userInFilterView.getDocCode()));
        }
        if (userInFilterView.getCitizenshipCode() != null) {
            user.setCitizenship(new Country());
            user.setCitizenship(countryDao.getByCode(userInFilterView.getCitizenshipCode()));
        }

        List<User> users = userDao.filter(user);

        return mapperFacade.mapAsList(users, UserOutFilterView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserView getById(Long id) {
        if (id == null) {
            throw new WrongRequestException("Field 'id' cannot be null");
        }

        User user = userDao.getById(id);

        if (user == null) {
            throw new RecordNotFoundException("Record with id = " + id + " was not found on User");
        }

        return mapperFacade.map(user, UserView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(UserUpdateView userUpdateView) {
        validateToUpdateView(userUpdateView);

        User user = userDao.getById(userUpdateView.getId());

        setParamForUpdate(userUpdateView, user);

        userDao.update(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(UserSaveView userSaveView) {
        validateToSaveView(userSaveView);
        User user = mapperFacade.map(userSaveView, User.class);

        if (userSaveView.getOfficeId() != null) {
            Office officeDaoById = officeDao.getById(userSaveView.getOfficeId());
            if (officeDaoById != null) {
                user.setOffice(officeDaoById);
            }
        }

        saveDocUser(userSaveView);

        if (userSaveView.getCitizenshipCode() != null) {
            user.setCitizenship(countryDao.getByCode(userSaveView.getCitizenshipCode()));
        }

        userDao.save(user);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    void saveDocUser(UserSaveView userSaveView) {
        DocUser docUser = new DocUser();

        if (userSaveView.getDocCode() != null) {
            docUser.setDocType(docDao.getByCode(userSaveView.getDocCode()));
        }
        if (userSaveView.getDocNumber() != null) {
            docUser.setDocNumber(userSaveView.getDocNumber());
        }
        if (userSaveView.getDocDate() != null) {
            docUser.setDocDate(userSaveView.getDocDate());
        }
    }

    private void setParamForUpdate(UserUpdateView userUpdateView, User user) {
        if (userUpdateView.getOfficeId() != null) {
            Office officeDaoById = officeDao.getById(userUpdateView.getOfficeId());
            if (officeDaoById != null) {
                user.setOffice(officeDaoById);
            }
        }

        user.setFirstName(userUpdateView.getFirstName());
        user.setPosition(userUpdateView.getPosition());

        if (userUpdateView.getSecondName() != null) {
            user.setSecondName(userUpdateView.getSecondName());
        }
        if (userUpdateView.getMiddleName() != null) {
            user.setMiddleName(userUpdateView.getMiddleName());
        }
        if (userUpdateView.getPhone() != null) {
            user.setPhone(userUpdateView.getPhone());
        }
        if (userUpdateView.getDocName() != null) {
            user.getDocUser().setDocType(docDao.getByName(userUpdateView.getDocName()));
        }
        if (userUpdateView.getDocName() != null) {
            user.getDocUser().setDocNumber(userUpdateView.getDocNumber());
        }
        if (userUpdateView.getDocName() != null) {
            user.getDocUser().setDocDate(userUpdateView.getDocDate());
        }
        if (userUpdateView.getCitizenshipCode() != null) {
            user.setCitizenship(countryDao.getByCode(userUpdateView.getCitizenshipCode()));
        }
        if (userUpdateView.getIdentified() != null) {
            user.setIdentified(userUpdateView.getIdentified());
        }
    }

    private boolean isFirstNameValid(String firstName) {
        return firstName.matches("[A-ZА-Яa-zа-я]{1,50}");
    }

    private boolean isSecondNameValid(String lastName) {
        return lastName.matches("[A-Za-zА-Яа-я -]{1,50}");
    }

    private boolean isMiddleNameValid(String middleName) {
        return middleName.matches("[A-Za-zА-Яа-я '-]{1,50}");
    }

    private boolean isPositionValid(String position) {
        return position.matches("[A-Za-zА-Яа-я0-9 ,-]{1,100}");
    }

    private boolean isDocNameValid(String docName) {
        return docName.matches("[A-Za-zА-Яа-я0-9 ,-]{1,50}");
    }

    private boolean isDocNumberValid(String docNumber) {
        return docNumber.matches("\\d{1,20}");
    }

    private boolean isPhoneValid(String phone) {
        return phone.matches("\\+?\\d{7,12}");
    }

    private void validateToUpdateView(UserUpdateView view) {
        StringBuilder messageBuilder = new StringBuilder("");
        if (view.getId() == null) {
            messageBuilder.append("Field 'id' is null. ");
        }
        validate(messageBuilder, view.getFirstName(), view.getSecondName(), view.getMiddleName(), view.getPosition(), view.getPhone(), view.getDocNumber());
        if (view.getDocName() != null && !isDocNameValid(view.getDocName())) {
            messageBuilder.append("Field 'docName' is invalid");
        }
        if (messageBuilder.length() > 0) {
            throw new WrongRequestException(messageBuilder.toString().trim());
        }
        if (userDao.getById(view.getId()) == null) {
            messageBuilder.append("Record with id = " + view.getId() + " was not found in Users. ");
        }
        validateReference(messageBuilder, view.getDocName(), view.getCitizenshipCode());
    }

    private void validateReference(StringBuilder messageBuilder, String docName, Long citizenshipCode) {
        if (docName != null && docDao.getByName(docName) == null) {
            messageBuilder.append("Doc with name = ").append(docName).append(" was not found. ");
        }
        if (citizenshipCode != null && countryDao.getByCode(citizenshipCode) == null) {
            messageBuilder.append("Country with code = ").append(citizenshipCode).append(" was not found.");
        }
        if (messageBuilder.length() > 0) {
            throw new RecordNotFoundException(messageBuilder.toString().trim());
        }
    }

    private void validateToSaveView(UserSaveView view) {
        StringBuilder messageBuilder = new StringBuilder("");
        validate(messageBuilder, view.getFirstName(), view.getSecondName(), view.getMiddleName(), view.getPosition(), view.getPhone(), view.getDocNumber());

        if (view.getOfficeId() == null) {
            messageBuilder.append("Field 'officeId' cannot be null. ");
        }
        if (view.getDocName() != null && !isDocNameValid(view.getDocName())) {
            messageBuilder.append("Field 'docName' is invalid");

            if (view.getDocCode() == null) {
                messageBuilder.append("Field 'docCode' is null . ");
            }
            if (view.getDocDate() == null) {
                messageBuilder.append("Field 'docDate' is null. ");
            }
            if (view.getCitizenshipCode() == null) {
                messageBuilder.append("Field 'citizenshipCode' is null. ");
            }
            if (view.getIdentified() == null) {
                messageBuilder.append("Field 'isIdentified' is null.");
            }
            if (messageBuilder.length() > 0) {
                throw new WrongRequestException(messageBuilder.toString().trim());
            }
            if (officeDao.getById(view.getOfficeId()) == null) {
                messageBuilder.append("Office with id = ").append(view.getOfficeId()).append(" was not found. ");
            }
            validateReference(messageBuilder, view.getDocName(), view.getCitizenshipCode());
        }
    }

    private void validate(StringBuilder messageBuilder, String firstName, String secondName, String middleName, String position, String phone, String docNumber) {
        if (firstName == null || !isFirstNameValid(firstName)) {
            messageBuilder.append("Field 'firstName' is null or invalid. ");
        }
        if (secondName != null && !isSecondNameValid(secondName)) {
            messageBuilder.append("Field 'secondName' is invalid. ");
        }
        if (middleName != null && !isMiddleNameValid(middleName)) {
            messageBuilder.append("Field 'middleName' is invalid. ");
        }
        if (position == null || !isPositionValid(position)) {
            messageBuilder.append("Field 'position' is null or invalid. ");
        }
        if (phone != null && !isPhoneValid(phone)) {
            messageBuilder.append("Field 'phone' is invalid. ");
        }
        if (docNumber != null && !isDocNumberValid(docNumber)) {
            messageBuilder.append("Field 'docNumber' is invalid. ");
        }
    }
}
