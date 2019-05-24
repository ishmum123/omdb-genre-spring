package com.newscred.omdb.genre.helpers.components;

import com.newscred.omdb.genre.database.entities.User;
import com.newscred.omdb.genre.database.entities.UserDetail;
import com.newscred.omdb.genre.database.entities.UserType;
import com.newscred.omdb.genre.database.repositories.UserDetailRepository;
import com.newscred.omdb.genre.database.repositories.UserRepository;
import com.newscred.omdb.genre.database.repositories.UserTypeRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserInfoGetterComponentTest {

    private User user;
    private List<User> userList;
    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;
    private UserDetailRepository userDetailRepository;
    private RepositoryHolderComponent holderComponent;
    private UserInfoGetterComponent component;

    @Before
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userTypeRepository = mock(UserTypeRepository.class);
        userDetailRepository = mock(UserDetailRepository.class);
        holderComponent = mock(RepositoryHolderComponent.class);
        when(holderComponent.getUserRepository()).thenReturn(userRepository);
        when(holderComponent.getUserTypeRepository()).thenReturn(userTypeRepository);
        when(holderComponent.getUserDetailRepository()).thenReturn(userDetailRepository);
        component = new UserInfoGetterComponent(holderComponent);
        user = new User();
        userList = Collections.singletonList(user);
    }

    @Test
    public void getUsers() {
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(userList, component.getUsers());
    }

    @Test
    public void getUserTypes() {
        UserType type = new UserType();
        List<UserType> list = Collections.singletonList(type);
        when(userTypeRepository.findAll()).thenReturn(list);
        assertEquals(list, component.getUserTypes());
    }

    @Test
    public void getUserDetails() {
        UserDetail detail = new UserDetail();
        List<UserDetail> list = Collections.singletonList(detail);
        when(userDetailRepository.findAll()).thenReturn(list);
        assertEquals(list, component.getUserDetails());
    }

    @Test
    public void getUsersByFirstname() {
        String testString = "sdfsdf";
        when(userRepository.findByFirstname(testString)).thenReturn(userList);
        assertEquals(userList, component.getUsersByFirstname(testString));
    }

    @Test
    public void getUsersByLastname() {
        String testString = "sdfsdf";
        when(userRepository.findByLastname(testString)).thenReturn(userList);
        assertEquals(userList, component.getUsersByLastname(testString));
    }

    @Test
    public void getUsersByName() {
        String testString = "sdfsdf";
        when(userRepository.findByName(testString)).thenReturn(userList);
        assertEquals(userList, component.getUsersByName(testString));
    }
}