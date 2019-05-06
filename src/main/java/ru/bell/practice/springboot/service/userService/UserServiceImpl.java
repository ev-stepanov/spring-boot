package ru.bell.practice.springboot.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bell.practice.springboot.dao.countryDao.CountryDao;
import ru.bell.practice.springboot.dao.docDao.DocDao;
import ru.bell.practice.springboot.dao.officeDao.OfficeDao;
import ru.bell.practice.springboot.dao.userDao.UserDao;
import ru.bell.practice.springboot.exception.RecordNotFoundException;
import ru.bell.practice.springboot.exception.WrongRequestException;
import ru.bell.practice.springboot.model.Country;
import ru.bell.practice.springboot.model.DocUser;
import ru.bell.practice.springboot.model.Office;
import ru.bell.practice.springboot.model.User;
import ru.bell.practice.springboot.model.mapper.MapperFacade;
import ru.bell.practice.springboot.view.userView.*;

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
        User user = mapperFacade.map(userInFilterView, User.class);
        Office office = officeDao.getById(userInFilterView.getOfficeId());
        if (office == null) {
            throw new RecordNotFoundException();
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
        User user = userDao.getById(id);
        if (user == null) {
            throw new RecordNotFoundException();
        }
        return mapperFacade.map(user, UserView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(UserUpdateView userUpdateView) {
        User user = userDao.getById(userUpdateView.getId());
        if (user == null) {
            throw new WrongRequestException();
        }
        setParamForUpdate(userUpdateView, user);
        userDao.update(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(UserSaveView userSaveView) {
        User user = mapperFacade.map(userSaveView, User.class);
        Office office = officeDao.getById(userSaveView.getOfficeId());
        if (office == null) {
            throw new WrongRequestException();
        }
        user.setOffice(office);
        user.setDocUser(saveDocUser(userSaveView));
        if (userSaveView.getCitizenshipCode() != null) {
            user.setCitizenship(countryDao.getByCode(userSaveView.getCitizenshipCode()));
        }
        userDao.save(user);
    }

    private DocUser saveDocUser(UserSaveView userSaveView) {
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
        return docUser;
    }

    private void setParamForUpdate(UserUpdateView userUpdateView, User user) {
        Office officeDaoById = officeDao.getById(userUpdateView.getOfficeId());
        if (officeDaoById == null) {
            throw new WrongRequestException();
        }
        user.setOffice(officeDaoById);
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
}