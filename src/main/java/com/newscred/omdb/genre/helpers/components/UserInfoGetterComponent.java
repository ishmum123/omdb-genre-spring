package com.newscred.omdb.genre.helpers.components;

import com.newscred.omdb.genre.config.ServiceConfiguration;
import com.newscred.omdb.genre.helpers.dataclass.UserShortSummary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserInfoGetterComponent {

    private final List<UserShortSummary> users;

    public UserInfoGetterComponent(ServiceConfiguration configuration) {
        users = configuration.getUsers();
    }

    public Optional<UserShortSummary> findByEmailAndPassword(String email, String password) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email) && user.getPassword().equalsIgnoreCase(password))
                .findFirst();
    }
}
