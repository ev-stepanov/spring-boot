package ru.bell.practice.springboot.service.countryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bell.practice.springboot.dao.countryDao.CountryDao;
import ru.bell.practice.springboot.model.Country;
import ru.bell.practice.springboot.model.mapper.MapperFacade;
import ru.bell.practice.springboot.view.countryView.CountryView;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final MapperFacade mapperFacade;

    private final CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao, MapperFacade mapperFacade) {
        this.countryDao = countryDao;
        this.mapperFacade = mapperFacade;
    }


    @Override
    public List<CountryView> list() {
        List<Country> list = countryDao.list();
        return mapperFacade.mapAsList(list, CountryView.class);
    }
}
