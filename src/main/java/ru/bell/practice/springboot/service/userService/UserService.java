package ru.bell.practice.springboot.service.userService;

import org.springframework.validation.annotation.Validated;
import ru.bell.practice.springboot.view.userView.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервиса для работы с пользователями
 */
@Validated
public interface UserService {
    /**
     * Возвращает отфильтрованный список ползователей
     *
     * @param userInFilterView фильтр
     * @return отфильтрованный список
     */
    List<UserOutFilterView> list(@Valid UserInFilterView userInFilterView);

    /**
     * Возвращает пользователя с указанным id.
     *
     * @param id идентификатор пользователя
     * @return пользователь с указанным id
     */
    UserView getById(Long id);

    /**
     * Обновляет данные о полщователе
     *
     * @param userUpdateView - содержит данные об обновленном пользователе
     */
    void update(@Valid UserUpdateView userUpdateView);

    /**
     * Сохраняет данные о новом пользователе
     *
     * @param userSaveView - содержит данные о новом пользователе
     */
    void save(@Valid UserSaveView userSaveView);
}
