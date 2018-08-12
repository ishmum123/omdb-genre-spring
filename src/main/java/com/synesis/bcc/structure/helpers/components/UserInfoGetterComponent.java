package com.synesis.bcc.structure.helpers.components;

import com.synesis.bcc.structure.database.entities.User;
import com.synesis.bcc.structure.database.entities.UserDetail;
import com.synesis.bcc.structure.database.entities.UserType;
import com.synesis.bcc.structure.database.repositories.UserDetailRepository;
import com.synesis.bcc.structure.database.repositories.UserRepository;
import com.synesis.bcc.structure.database.repositories.UserTypeRepository;
import com.synesis.bcc.structure.helpers.exceptions.ServiceExceptionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserInfoGetterComponent {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final UserDetailRepository userDetailRepository;

    public UserInfoGetterComponent(@Autowired RepositoryHolderComponent repositoryHolderComponent) {
        this.userRepository = repositoryHolderComponent.getUserRepository();
        this.userTypeRepository = repositoryHolderComponent.getUserTypeRepository();
        this.userDetailRepository = repositoryHolderComponent.getUserDetailRepository();
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Iterable<UserType> getUserTypes() {
        return userTypeRepository.findAll();
    }

    public Iterable<UserDetail> getUserDetails() {
        return userDetailRepository.findAll();
    }

    public Iterable<User> getUsersByFirstname(String firstname) {
        return userRepository.findByFirstname(firstname);
    }

    public Iterable<User> getUsersByLastname(String lastname) {
        return userRepository.findByLastname(lastname);
    }

    public Iterable<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    public User findById(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(() -> new ServiceExceptionHolder.UserNotFoundException(uuid));
    }
}
