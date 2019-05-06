package ru.bell.practice.springboot.service.organizationService;

import org.springframework.validation.annotation.Validated;
import ru.bell.practice.springboot.view.organizationView.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Интерфейс сервиса для работы с организациями
 */
@Validated
public interface OrganizationService {

    /**
     * Возвращает отфильтрованный список
     *
     * @param organizationInFilterView фильтр
     * @return отфильтрованный список
     */
    List<OrganizationOutFilterView> list(@Valid OrganizationInFilterView organizationInFilterView);

    /**
     * Возвращает организацию с указанным id.
     *
     * @param id идентификатор организации
     * @return организация с указанным id
     */
    OrganizationView getById(Long id);

    /**
     * Обновляет данные об организации
     *
     * @param organizationUpdateView - содержит данные об обновленном организации
     */
    void update(@Valid OrganizationUpdateView organizationUpdateView);

    /**
     * Сохраняет данные о новой организации
     *
     * @param organizationSaveView - содержит данные о новой организации
     */
    void save(@Valid OrganizationSaveView organizationSaveView);
}
