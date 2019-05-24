package com.newscred.omdb.genre.controllers;

import com.newscred.omdb.genre.helpers.dataclass.UserShortSummary;
import com.newscred.omdb.genre.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    // TODO: Provide a token for checking requests
    @GetMapping("sign-in/{email}/{password}")
    public Optional<UserShortSummary> signIn(@PathVariable String email, @PathVariable String password) {
        return userService.signIn(email, password);
    }

}
