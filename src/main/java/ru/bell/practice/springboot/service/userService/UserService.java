package ru.bell.practice.springboot.service.userService;

import ru.bell.practice.springboot.view.userView.*;

import java.util.List;

/**
 * Интерфейс сервиса для работы с пользователями
 */
public interface UserService {
    /**
     * Возвращает отфильтрованный список ползователей
     *
     * @param userInFilterView фильтр
     * @return отфильтрованный список
     */
    List<UserOutFilterView> list(UserInFilterView userInFilterView);

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
    void update(UserUpdateView userUpdateView);

    /**
     * Сохраняет данные о новом пользователе
     *
     * @param userSaveView - содержит данные о новом пользователе
     */
    void save(UserSaveView userSaveView);
}
