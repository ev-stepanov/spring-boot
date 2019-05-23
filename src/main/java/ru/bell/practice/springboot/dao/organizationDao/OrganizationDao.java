package ru.bell.practice.springboot.dao.organizationDao;

import ru.bell.practice.springboot.model.Organization;

import java.util.List;

/**
 * DAO для работы с организациями
 */

public interface OrganizationDao {

    /**
     * Возвращает отфильтрованный список организаций.
     *
     * @return отфильтрованный список
     */
    List<Organization> filter(String name, String inn, Boolean isActive);

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