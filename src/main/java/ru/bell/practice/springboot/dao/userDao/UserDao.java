package ru.bell.practice.springboot.dao.userDao;

import ru.bell.practice.springboot.model.User;

import java.util.List;

/**
 * Интерфейс DAO для работы с пользователями
 */
public interface UserDao {

    /**
     * Возвращает отфильтрованный список пользователей.
     *
     * @return отфильтрованный список
     */
    List<User> filter(Long officeId, String firstName, String secondName, String middleName,
                      String position, Long docCode, Long citizenshipCode);

    /**
     * Возвращает пользователя с указанным идентификатором.
     *
     * @param id идентификатор пользователя
     * @return пользователь с указанным идентификатором
     */
    User getById(Long id);

    /**
     * Обнавляет сведения о пользователе.
     *
     * @param user содержит обновленные сведения о пользователе
     */
    void update(User user);

    /**
     * Сохраняет сведения об организации.
     *
     * @param user содержит сведения об сохраняемом пользователе
     */
    void save(User user);
}
