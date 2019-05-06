package ru.bell.practice.springboot.service.officeService;

import org.springframework.validation.annotation.Validated;
import ru.bell.practice.springboot.view.officeView.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Интерфейс сервиса для работы с офисами
 */
@Validated
public interface OfficeService {

    /**
     * Возвращает отфильтрованный список офисов.
     *
     * @param officeInFilterView - фильтр
     * @return отфильтрованный список
     */
    List<OfficeOutFilterView> list(@Valid OfficeInFilterView officeInFilterView);

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
    void update(@Valid OfficeUpdateView officeUpdateView);

    /**
     * Сохраняет данные о новом офисе.
     *
     * @param officeSaveView - содержит данные о новом офисе
     */
    void save(@Valid OfficeSaveView officeSaveView);
}