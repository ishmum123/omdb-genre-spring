package com.synesis.bcc.structure.controllers;

import com.synesis.bcc.structure.database.entities.User;
import com.synesis.bcc.structure.database.entities.UserDetail;
import com.synesis.bcc.structure.database.entities.UserType;
import com.synesis.bcc.structure.helpers.dataclass.UserSummary;
import com.synesis.bcc.structure.helpers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void addUser(@RequestBody @Valid UserSummary userSummary) throws Exception {
        userService.addUserSummary(userSummary);
    }

    @GetMapping("type")
    public Iterable<UserType> getUserTypes() {
        return userService.getUserTypes();
    }

    @PostMapping("type")
    public void addUserType(@RequestBody @Valid UserType userType) {
        userService.addUserType(userType);
    }

    @GetMapping("detail")
    public Iterable<UserDetail> getUserDetails() {
        return userService.getUserDetails();
    }

}
