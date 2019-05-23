package ru.bell.practice.springboot.dao.countryDao;

import ru.bell.practice.springboot.model.Country;

import java.util.List;

/**
 * DAO для работы с гражданством
 */
public interface CountryDao {

    /**
     * Возвращает список стран и их кодов.
     *
     * @return список стран и их кодов
     */
    List<Country> list();

    /**
     * Возвращает гражданство по коду.
     *
     * @param code код гражданства
     * @return гражданство
     */
    Country getByCode(Long code);
}
