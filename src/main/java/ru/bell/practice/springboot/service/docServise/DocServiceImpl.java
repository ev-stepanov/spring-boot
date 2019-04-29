package ru.bell.practice.springboot.service.docServise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bell.practice.springboot.dao.docDao.DocDao;
import ru.bell.practice.springboot.model.DocType;
import ru.bell.practice.springboot.model.mapper.MapperFacade;
import ru.bell.practice.springboot.view.docView.DocView;

import java.util.List;

/**
 * Сервис удовстверений
 */
@Service
public class DocServiceImpl implements DocService {

    private final MapperFacade mapperFacade;
    private final DocDao docDao;

    /**
     * Конструктор
     *
     * @param docDao DAO слой для работы с удовстверениями
     * @param mapperFacade Фасад для преобразования между моделями БД и фронта
     */
    @Autowired
    public DocServiceImpl(DocDao docDao, MapperFacade mapperFacade) {
        this.docDao = docDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocView> list() {
        List<DocType> list = docDao.list();
        return mapperFacade.mapAsList(list, DocView.class);
    }
}