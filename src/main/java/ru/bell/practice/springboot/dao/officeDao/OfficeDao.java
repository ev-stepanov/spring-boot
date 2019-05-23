package ru.bell.practice.springboot.dao.officeDao;

import ru.bell.practice.springboot.model.Office;

import java.util.List;

/**
 * DAO для работы с офисами
 */
public interface OfficeDao {

    /**
     * Возвращает отфильтрованный список офисов.
     *
     * @param orgId ид организации
     * @param name имя организации
     * @param phone телефон
     * @param isActive активность
     * @return отфильтрованный список
     */
    List<Office> filter(Long orgId, String name, String phone, Boolean isActive);

    /**
     * Возвращает офис с указанным идентификатором.
     *
     * @param id идентификатор офиса
     * @return офис с указанным идентификатором
     */
    Office getById(Long id);

    /**
     * Обнавляет сведения об офисе.
     *
     * @param office - содержит обновленные сведения об офисе
     */
    void update(Office office);

    /**
     * Сохраняет сведения о новом офисе.
     *
     * @param office - содержит сведения о новом офисе
     */
    void save(Office office);
}
