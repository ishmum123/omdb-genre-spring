package com.synesis.bcc.structure.helpers.services;

import com.synesis.bcc.structure.database.entities.User;
import com.synesis.bcc.structure.database.entities.UserDetail;
import com.synesis.bcc.structure.database.entities.UserType;
import com.synesis.bcc.structure.helpers.components.UserInfoGetterComponent;
import com.synesis.bcc.structure.helpers.components.UserInfoSetterComponent;
import com.synesis.bcc.structure.helpers.dataclass.UserSummary;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserInfoGetterComponent getterComponent;
    private UserInfoSetterComponent setterComponent;
    private UserService service;

    @Before
    public void setUp() throws Exception {
        getterComponent = mock(UserInfoGetterComponent.class);
        setterComponent = mock(UserInfoSetterComponent.class);
        service = new UserService(getterComponent, setterComponent);
    }

    @Test
    public void getUsers() {
        User user = new User();
        List<User> list = Collections.singletonList(user);
        when(getterComponent.getUsers()).thenReturn(list);
        assertEquals(list, service.getUsers());
    }

    @Test
    public void addUserSummary() throws Exception {
        UserSummary summary = new UserSummary();
        service.addUserSummary(summary);
        verify(setterComponent).addUserSummary(summary);
    }

    @Test
    public void getUserTypes() {
        UserType type = new UserType();
        List<UserType> list = Collections.singletonList(type);
        when(getterComponent.getUserTypes()).thenReturn(list);
        assertEquals(list, service.getUserTypes());
    }

    @Test
    public void addUserType() {
        UserType type = new UserType();
        service.addUserType(type);
        verify(setterComponent).addUserType(type);
    }

    @Test
    public void getUserDetails() {
        UserDetail detail = new UserDetail();
        List<UserDetail> list = Collections.singletonList(detail);
        when(getterComponent.getUserDetails()).thenReturn(list);
        assertEquals(list, service.getUserDetails());
    }

    @Test
    public void getUsersByFirstname() {
        String testString = "sdfs";
        User user = new User();
        List<User> list = Collections.singletonList(user);
        when(getterComponent.getUsersByFirstname(testString)).thenReturn(list);
        assertEquals(list, service.getUsersByFirstname(testString));
    }

    @Test
    public void getUsersByLastname() {
        String testString = "sdfs";
        User user = new User();
        List<User> list = Collections.singletonList(user);
        when(getterComponent.getUsersByLastname(testString)).thenReturn(list);
        assertEquals(list, service.getUsersByLastname(testString));
    }

    @Test
    public void getUsersByName() {
        String testString = "sdfs";
        User user = new User();
        List<User> list = Collections.singletonList(user);
        when(getterComponent.getUsersByName(testString)).thenReturn(list);
        assertEquals(list, service.getUsersByName(testString));
    }
}