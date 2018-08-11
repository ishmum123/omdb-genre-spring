package com.synesis.bcc.structure.helpers.components;

import com.synesis.bcc.structure.database.entities.*;
import com.synesis.bcc.structure.database.repositories.*;
import com.synesis.bcc.structure.helpers.dataclass.UserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserInfoSetterComponent {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final DesignationRepository designationRepository;
    private final UserTypeRepository userTypeRepository;
    private final UserStateRepository userStateRepository;

    public UserInfoSetterComponent(@Autowired RepositoryHolderComponent repositoryHolderComponent) {
        this.userRepository = repositoryHolderComponent.getUserRepository();
        this.designationRepository = repositoryHolderComponent.getDesignationRepository();
        this.userTypeRepository = repositoryHolderComponent.getUserTypeRepository();
        this.userStateRepository = repositoryHolderComponent.getUserStateRepository();
        this.userDetailRepository = repositoryHolderComponent.getUserDetailRepository();
    }

    @Transactional
    public void addUserSummary(UserSummary userSummary) throws Exception {
        User user = getUserFromSummary(userSummary);
        userRepository.save(user);
        userDetailRepository.save(getUserDetailFromSummary(userSummary, user));
    }

    private UserDetail getUserDetailFromSummary(UserSummary summary, User user) throws Exception {
        UserDetail detail = new UserDetail();

        Optional<Designation> optionalDesignation = designationRepository.findById(summary.getDesignation());
        optionalDesignation.ifPresent(detail::setDesignation);
        optionalDesignation.orElseThrow(() -> getFormedException("Designation"));

        Optional<UserType> optionalUserType = userTypeRepository.findById(summary.getType());
        optionalUserType.ifPresent(detail::setType);
        optionalUserType.orElseThrow(() -> getFormedException("User Type"));

        Optional<UserState> optionalUserState = userStateRepository.findById(summary.getState());
        optionalUserState.ifPresent(detail::setState);
        optionalUserState.orElseThrow(() -> getFormedException("User State"));

        detail.setUser(user);
        return detail;
    }

    private IllegalArgumentException getFormedException(String s) {
        return new IllegalArgumentException("Invalid " + s + " Provided");
    }

    private User getUserFromSummary(UserSummary summary) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstname(summary.getFirstname());
        user.setMiddlename(summary.getMiddlename());
        user.setLastname(summary.getLastname());
        user.setEmail(summary.getEmail());
        user.setPassword(summary.getPassword());
        user.setPhone(summary.getPhone());
        user.setAddress(summary.getAddress());
        return user;
    }

    public void addUserType(UserType userType) {
        userTypeRepository.save(userType);
    }

}
