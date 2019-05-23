package ru.bell.practice.springboot.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bell.practice.springboot.dao.countryDao.CountryDao;
import ru.bell.practice.springboot.dao.docDao.DocDao;
import ru.bell.practice.springboot.dao.officeDao.OfficeDao;
import ru.bell.practice.springboot.dao.userDao.UserDao;
import ru.bell.practice.springboot.exception.*;
import ru.bell.practice.springboot.model.*;
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
    public List<UserOutFilterView> list(UserInFilterView view) {
        List<User> users = userDao.filter(view.getOfficeId(), view.getFirstName(),view.getSecondName(),
                view.getMiddleName(), view.getPosition(), view.getDocCode(), view.getCitizenshipCode());

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
        UserView view = mapperFacade.map(user, UserView.class);

        if (user.getCitizenship() != null) {
            view.setCitizenshipCode(user.getCitizenship().getCode());
            view.setCitizenshipName(user.getCitizenship().getName());
        }
        if (user.getDocUser() != null) {
            view.setDocNumber(user.getDocUser().getDocNumber());
            view.setDocDate(user.getDocUser().getDocDate());
            if (user.getDocUser().getDocType() != null) {
                view.setDocName(user.getDocUser().getDocType().getName());
            }
        }

        return view;
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

        if (userUpdateView.getOfficeId() != null) {
            Office officeDaoById = officeDao.getById(userUpdateView.getOfficeId());
            if (officeDaoById == null) {
                throw new WrongRequestException();
            }
            user.setOffice(officeDaoById);
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
        if (userUpdateView.getDocNumber() != null) {
            user.getDocUser().setDocNumber(userUpdateView.getDocNumber());
        }
        if (userUpdateView.getDocDate() != null) {
            user.getDocUser().setDocDate(userUpdateView.getDocDate());
        }
        if (userUpdateView.getCitizenshipCode() != null) {
            user.setCitizenship(countryDao.getByCode(userUpdateView.getCitizenshipCode()));
        }
        if (userUpdateView.getIdentified() != null) {
            user.setIdentified(userUpdateView.getIdentified());
        }
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
}