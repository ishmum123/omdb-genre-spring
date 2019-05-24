package com.newscred.omdb.genre.controllers;

import com.newscred.omdb.genre.database.entities.User;
import com.newscred.omdb.genre.database.entities.UserDetail;
import com.newscred.omdb.genre.database.entities.UserType;
import com.newscred.omdb.genre.helpers.dataclass.UserSummary;
import com.newscred.omdb.genre.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @GetMapping("find")
    public Iterable<User> getUsersByName(
            @RequestParam(value = "firstname", required = false) String firstname,
            @RequestParam(value = "lastname", required = false) String lastname,
            @RequestParam(value = "name", required = false) String name
    ) {
        if (!StringUtils.isEmpty(firstname)) return userService.getUsersByFirstname(firstname);
        if (!StringUtils.isEmpty(lastname)) return userService.getUsersByLastname(lastname);
        if (!StringUtils.isEmpty(name)) return userService.getUsersByName(name);
        return new ArrayList<>();
    }

    @Transactional
    @PostMapping
    public void addUser(@RequestBody @Valid UserSummary userSummary) throws Exception {
        userService.addUserSummary(userSummary);
    }

    @GetMapping("type")
    public Iterable<UserType> getUserTypes() {
        return userService.getUserTypes();
    }

    @Transactional
    @PostMapping("type")
    public void addUserType(@RequestBody @Valid UserType userType) {
        userService.addUserType(userType);
    }

    @GetMapping("detail")
    public Iterable<UserDetail> getUserDetails() {
        return userService.getUserDetails();
    }

}
