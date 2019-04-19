package ru.bell.practice.springboot.dao.countryDao;

import ru.bell.practice.springboot.model.Country;

import java.util.List;

public interface CountryDao {
    List<Country> list();
}
