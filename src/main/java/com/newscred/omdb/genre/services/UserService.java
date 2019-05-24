package com.newscred.omdb.genre.services;

import com.newscred.omdb.genre.database.entities.User;
import com.newscred.omdb.genre.database.entities.UserDetail;
import com.newscred.omdb.genre.database.entities.UserType;
import com.newscred.omdb.genre.helpers.components.UserInfoGetterComponent;
import com.newscred.omdb.genre.helpers.components.UserInfoSetterComponent;
import com.newscred.omdb.genre.helpers.dataclass.UserSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserInfoGetterComponent getterComponent;
    private final UserInfoSetterComponent setterComponent;

    public Iterable<User> getUsers() {
        return getterComponent.getUsers();
    }

    public void addUserSummary(UserSummary userSummary) throws Exception {
        setterComponent.addUserSummary(userSummary);
    }

    public Iterable<UserType> getUserTypes() {
        return getterComponent.getUserTypes();
    }

    public void addUserType(UserType userType) {
        setterComponent.addUserType(userType);
    }

    public Iterable<UserDetail> getUserDetails() {
        return getterComponent.getUserDetails();
    }

    public Iterable<User> getUsersByFirstname(String firstname) {
        return getterComponent.getUsersByFirstname(firstname);
    }

    public Iterable<User> getUsersByLastname(String lastname) {
        return getterComponent.getUsersByLastname(lastname);
    }

    public Iterable<User> getUsersByName(String name) {
        return getterComponent.getUsersByName(name);
    }

    public User findById(UUID uuid) {
        return getterComponent.findById(uuid);
    }
}
