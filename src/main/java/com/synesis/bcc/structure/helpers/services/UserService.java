package com.synesis.bcc.structure.helpers.services;

import com.synesis.bcc.structure.database.entities.User;
import com.synesis.bcc.structure.database.entities.UserDetail;
import com.synesis.bcc.structure.database.entities.UserType;
import com.synesis.bcc.structure.helpers.components.UserInfoGetterComponent;
import com.synesis.bcc.structure.helpers.components.UserInfoSetterComponent;
import com.synesis.bcc.structure.helpers.dataclass.UserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserInfoGetterComponent getterComponent;
    private final UserInfoSetterComponent setterComponent;

    public UserService(@Autowired UserInfoGetterComponent getterComponent,
                       @Autowired UserInfoSetterComponent setterComponent) {
        this.setterComponent = setterComponent;
        this.getterComponent = getterComponent;
    }

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
