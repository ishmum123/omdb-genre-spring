package com.synesis.bcc.structure.helpers.components;

import com.synesis.bcc.structure.database.entities.User;
import com.synesis.bcc.structure.database.entities.UserDetail;
import com.synesis.bcc.structure.database.entities.UserType;
import com.synesis.bcc.structure.database.repositories.*;
import com.synesis.bcc.structure.helpers.dataclass.UserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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
        designationRepository.findById(summary.getDesignation()).ifPresent(detail::setDesignation);
        userTypeRepository.findById(summary.getType()).ifPresent(detail::setType);
        userStateRepository.findById(summary.getState()).ifPresent(detail::setState);
        detail.setUser(user);
        return detail;
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
