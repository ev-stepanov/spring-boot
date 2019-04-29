package ru.bell.practice.springboot.service.officeService;

import ru.bell.practice.springboot.view.officeView.*;

import java.util.List;

/**
 * Интерфейс сервиса для работы с офисами
 */
public interface OfficeService {

    /**
     * Возвращает отфильтрованный список офисов.
     *
     * @param officeInFilterView - фильтр
     * @return отфильтрованный список
     */
    List<OfficeOutFilterView> list(OfficeInFilterView officeInFilterView);

    /**
     * Возвращает офис с указанным id.
     *
     * @param id - идентификатор
     * @return офис с указанным id
     */
    OfficeView getById(Long id);

    /**
     * Обновляет данные об офисе
     *
     * @param officeUpdateView - содержит обновляемые данные об офисе
     */
    void update(OfficeUpdateView officeUpdateView);

    /**
     * Сохраняет данные о новом офисе.
     *
     * @param officeSaveView - содержит данные о новом офисе
     */
    void save(OfficeSaveView officeSaveView);
}