package ru.bell.practice.springboot.service.countryService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bell.practice.springboot.dao.countryDao.CountryDao;
import ru.bell.practice.springboot.view.countryView.CountryView;

import java.util.List;

import static org.junit.Assert.*;

public class CountryServiceImplTest {
    @Autowired
    private CountryService service;

    @Test
    public void listNotNull() {
    }
}