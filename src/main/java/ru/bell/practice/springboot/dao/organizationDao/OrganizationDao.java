package ru.bell.practice.springboot.dao.organizationDao;

import ru.bell.practice.springboot.model.Organization;

import java.util.List;

/**
 * Интерфейс DAO для работы с организациями
 */
public interface OrganizationDao {

    /**
     * Возвращает отфильтрованный список организаций.
     *
     * @param organization фильтр списка организаций
     * @return отфильтрованный список
     */
    List<Organization> filter(Organization organization);

    /**
     * Возвращает организацию с указанным идентификатором.
     *
     * @param id идентификатор организации
     * @return организация с указанным идентификатором
     */
    Organization getById(Long id);

    /**
     * Обнавляет сведения об организации.
     *
     * @param organization содержит обновленные сведения об организации
     */
    void update(Organization organization);

    /**
     * Сохраняет сведения об организации.
     *
     * @param organization содержит сведения об сохраняемой организации
     */
    void save(Organization organization);
}