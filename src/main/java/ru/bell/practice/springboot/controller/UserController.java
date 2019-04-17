package ru.bell.practice.springboot.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ru.bell.practice.springboot.view.userView.UserFilterView;
import ru.bell.practice.springboot.view.userView.UserSaveView;
import ru.bell.practice.springboot.view.userView.UserUpdateView;
import ru.bell.practice.springboot.view.userView.UserView;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {

    @ApiOperation(value = "Get user list by filter", httpMethod = "POST")
    @PostMapping("/list")
    public List<UserView> getUsersByFilter (@RequestBody UserFilterView userFilterView){
        return Collections.emptyList();
    }

    @ApiOperation(value = "Get the user by id", httpMethod = "GET")
    @GetMapping("/{id}")
    public UserView getUserById (@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "Update of information about the user", httpMethod = "POST")
    @PostMapping("/update")
    public Boolean update (@RequestBody UserUpdateView userUpdateView) {
        return false;
    }

    @ApiOperation(value = "Save of information about the user", httpMethod = "POST")
    @PostMapping("/save")
    public Boolean save (@RequestBody UserSaveView userSaveView) {
        return false;
    }
}