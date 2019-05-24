package com.newscred.omdb.genre.controllers;

import com.newscred.omdb.genre.database.entities.User;
import com.newscred.omdb.genre.database.entities.UserDetail;
import com.newscred.omdb.genre.database.entities.UserType;
import com.newscred.omdb.genre.helpers.dataclass.UserSummary;
import com.newscred.omdb.genre.services.UserService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private UserService service;
    private UserController controller;

    @Before
    public void setUp() {
        service = mock(UserService.class);
        controller = new UserController(service);
    }

    @Test
    public void getUsers() {
        Iterable<User> users = Collections.singletonList(new User());
        when(service.getUsers()).thenReturn(users);
        assertEquals(users, controller.getUsers());
    }

    @Test
    public void getUsersByName_callsFirstnameIfFirstnameAvailable() {
        String testString = "asdfs";

        User user = new User();
        List<User> list = Collections.singletonList(user);

        User differentUser = new User();
        List<User> differentList = Arrays.asList(user, differentUser);

        when(service.getUsersByFirstname(testString)).thenReturn(list);
        when(service.getUsersByLastname(testString)).thenReturn(differentList);
        when(service.getUsersByName(testString)).thenReturn(differentList);

        assertEquals(list, controller.getUsersByName(testString, null, null));
        assertEquals(list, controller.getUsersByName(testString, "", ""));
    }

    @Test
    public void getUsersByName_callsLastnameIfLastnameAvailableButNotFirst() {
        String testString = "asdfs";

        User user = new User();
        List<User> list = Collections.singletonList(user);

        User differentUser = new User();
        List<User> differentList = Arrays.asList(user, differentUser);

        when(service.getUsersByFirstname(testString)).thenReturn(list);
        when(service.getUsersByLastname(testString)).thenReturn(list);
        when(service.getUsersByName(testString)).thenReturn(differentList);

        assertEquals(list, controller.getUsersByName(null, testString, null));
        assertEquals(list, controller.getUsersByName("", testString, ""));
    }

    @Test
    public void getUsersByName_callsNameIfNotLastAndFirstNotSpecified() {
        String testString = "asdfs";

        User user = new User();
        List<User> list = Collections.singletonList(user);

        User differentUser = new User();
        List<User> differentList = Arrays.asList(user, differentUser);

        when(service.getUsersByFirstname(testString)).thenReturn(differentList);
        when(service.getUsersByLastname(testString)).thenReturn(differentList);
        when(service.getUsersByName(testString)).thenReturn(list);

        assertEquals(list, controller.getUsersByName(null, null, testString));
        assertEquals(list, controller.getUsersByName("", "", testString));
    }

    @Test
    public void addUser() throws Exception {
        UserSummary userSummary = new UserSummary();
        controller.addUser(userSummary);
        verify(service).addUserSummary(userSummary);
    }

    @Test
    public void getUserTypes() {
        UserType type = new UserType();
        List<UserType> list = Collections.singletonList(type);
        when(service.getUserTypes()).thenReturn(list);
        assertEquals(list, controller.getUserTypes());
    }

    @Test
    public void addUserType() {
        UserType type = new UserType();
        controller.addUserType(type);
        verify(service).addUserType(type);
    }

    @Test
    public void getUserDetails() {
        UserDetail detail = new UserDetail();
        List<UserDetail> list = Collections.singletonList(detail);
        when(service.getUserDetails()).thenReturn(list);
        assertEquals(list, controller.getUserDetails());
    }
}