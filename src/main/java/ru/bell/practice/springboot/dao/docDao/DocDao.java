package ru.bell.practice.springboot.dao.docDao;

import ru.bell.practice.springboot.model.DocType;

import java.util.List;

/**
 * Интурфейс DAO удовстверений
 */
public interface DocDao {

    /**
     * Возвращает список документов и их кодов
     * @return список документов и их кодов
     */
    List<DocType> list();

    /**
     * Возвращает документ с указанным именем
     *
     * @return документ
     */
    DocType getByName(String docName);


    /**
     * Возвращает документ с указанным code
     *
     * @return документ
     */
    DocType getByCode(Long docCode);
}
