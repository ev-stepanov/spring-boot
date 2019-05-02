package ru.bell.practice.springboot.dao.docUser;

import ru.bell.practice.springboot.model.DocUser;

/**
 * Дао для работы с документами конткетного пользователя
 */
public interface DocUserDao {
    /**
     * Сохранение документов пользователя
     * @param docUser документы пользователя
     */
    void save (DocUser docUser);
}
