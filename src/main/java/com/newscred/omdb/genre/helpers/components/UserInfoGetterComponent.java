package com.newscred.omdb.genre.helpers.components;

import com.newscred.omdb.genre.database.entities.User;
import com.newscred.omdb.genre.database.entities.UserDetail;
import com.newscred.omdb.genre.database.entities.UserType;
import com.newscred.omdb.genre.database.repositories.UserDetailRepository;
import com.newscred.omdb.genre.database.repositories.UserRepository;
import com.newscred.omdb.genre.database.repositories.UserTypeRepository;
import com.newscred.omdb.genre.helpers.exceptions.ServiceExceptionHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserInfoGetterComponent {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final UserDetailRepository userDetailRepository;

    public UserInfoGetterComponent(RepositoryHolderComponent repositoryHolderComponent) {
        userRepository = repositoryHolderComponent.getUserRepository();
        userTypeRepository = repositoryHolderComponent.getUserTypeRepository();
        userDetailRepository = repositoryHolderComponent.getUserDetailRepository();
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
