package ru.bell.practice.springboot.service.countryService;

import org.springframework.stereotype.Service;
import ru.bell.practice.springboot.view.countryView.CountryView;

import java.util.List;

public interface CountryService {
    List<CountryView> list();
}
