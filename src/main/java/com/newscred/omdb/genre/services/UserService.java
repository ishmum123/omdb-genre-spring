package com.newscred.omdb.genre.services;

import com.newscred.omdb.genre.helpers.components.UserInfoGetterComponent;
import com.newscred.omdb.genre.helpers.dataclass.UserShortSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserInfoGetterComponent getterComponent;

    public Optional<UserShortSummary> signIn(String email, String password) {
        return getterComponent.findByEmailAndPassword(email, password);
    }
}
