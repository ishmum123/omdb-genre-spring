package com.newscred.omdb.genre.helpers.components;

import com.newscred.omdb.genre.database.entities.*;
import com.newscred.omdb.genre.database.repositories.*;
import com.newscred.omdb.genre.helpers.dataclass.UserSummary;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserInfoSetterComponentTest {

    private DesignationRepository designationRepository;
    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;
    private UserStateRepository userStateRepository;
    private UserDetailRepository userDetailRepository;
    private RepositoryHolderComponent holderComponent;
    private UserInfoSetterComponent component;

    @Before
    public void setUp() {
        designationRepository = mock(DesignationRepository.class);
        userRepository = mock(UserRepository.class);
        userTypeRepository = mock(UserTypeRepository.class);
        userStateRepository = mock(UserStateRepository.class);
        userDetailRepository = mock(UserDetailRepository.class);
        holderComponent = new RepositoryHolderComponent(userRepository, designationRepository, userDetailRepository,
                userStateRepository, userTypeRepository);
        component = new UserInfoSetterComponent(holderComponent);
    }

    @Test
    public void addUserSummary() throws Exception {
        String firstname = "Test";
        String lastname = "User";
        String email = "test@email.com";
        int designationId = 401;
        int typeId = 402;
        int stateId = 404;

        UserSummary summary = new UserSummary();
        summary.setFirstname(firstname);
        summary.setLastname(lastname);
        summary.setEmail(email);
        summary.setDesignation(designationId);
        summary.setType(typeId);
        summary.setState(stateId);

        Designation designation = new Designation();
        UserType type = new UserType();
        UserState state = new UserState();

        when(designationRepository.findById(designationId)).thenReturn(Optional.of(designation));
        when(userTypeRepository.findById(typeId)).thenReturn(Optional.of(type));
        when(userStateRepository.findById(stateId)).thenReturn(Optional.of(state));

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<UserDetail> userDetailArgumentCaptor = ArgumentCaptor.forClass(UserDetail.class);

        component.addUserSummary(summary);

        verify(userRepository).save(userArgumentCaptor.capture());
        verify(userDetailRepository).save(userDetailArgumentCaptor.capture());

        User user = userArgumentCaptor.getValue();

        assertEquals(firstname, user.getFirstname());
        assertEquals(lastname, user.getLastname());
        assertEquals(email, user.getEmail());

        UserDetail userDetail = userDetailArgumentCaptor.getValue();

        assertEquals(designation, userDetail.getDesignation());
        assertEquals(type, userDetail.getType());
        assertEquals(state, userDetail.getState());
        assertEquals(user, userDetail.getUser());
    }

    @Test
    public void addUserType() {
        UserType type = new UserType();
        component.addUserType(type);
        verify(userTypeRepository).save(type);
    }
}