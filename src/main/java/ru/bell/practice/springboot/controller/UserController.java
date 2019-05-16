package ru.bell.practice.springboot.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bell.practice.springboot.service.userService.UserService;
import ru.bell.practice.springboot.view.userView.*;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для работы с пользователями
 */
@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    /**
     * Конструктор
     * @param userService сервис пользователей
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Возвращает отфильтрованный список пользователей.
     * @param userInFilterView фильтр
     * @return отфильтрованный список пользователей
     */
    @ApiOperation(value = "Get user list by filter", httpMethod = "POST")
    @PostMapping("/list")
    public List<UserOutFilterView> getUsersByFilter (@RequestBody UserInFilterView userInFilterView){
        return userService.list(userInFilterView);
    }

    /**
     * Возвращает пользователя с указанным id
     * @param id идентификатор пользователя
     * @return пользователь с указанным id
     */
    @ApiOperation(value = "Get the user by id", httpMethod = "GET")
    @GetMapping("/{id:[\\d]+}")
    public UserView getUserById (@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    /**
     * Обновляет данные пользователя
     * @param userUpdateView обновленные данные пользователя
     */
    @ApiOperation(value = "Update of information about the user", httpMethod = "POST")
    @PostMapping("/update")
    public void update (@RequestBody UserUpdateView userUpdateView) {
        userService.update(userUpdateView);
    }

    /**
     * Сохраняет данные о новом пользователе
     * @param userSaveView данные нового пользователя
     */
    @ApiOperation(value = "Save of information about the user", httpMethod = "POST")
    @PostMapping("/save")
    public void save (@RequestBody UserSaveView userSaveView) {
        userService.save(userSaveView);
    }
}