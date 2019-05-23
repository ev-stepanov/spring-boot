package ru.bell.practice.springboot.service.countryService;

import ru.bell.practice.springboot.view.countryView.CountryView;

import java.util.List;

/**
 * Сервиса для работы с гражданствами
 */
public interface CountryService {

    /**
     * Возвращает список стран и их кодов.
     * @return список стран и их кодов
     */
    List<CountryView> list();
}
