package com.synesis.bcc.structure.helpers.components;

import com.synesis.bcc.structure.database.repositories.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class RepositoryHolderComponent {

    private final UserRepository userRepository;
    private final DesignationRepository designationRepository;
    private final UserDetailRepository userDetailRepository;
    private final UserStateRepository userStateRepository;
    private final UserTypeRepository userTypeRepository;

    public RepositoryHolderComponent(@Autowired UserRepository userRepository,
                                     @Autowired DesignationRepository designationRepository,
                                     @Autowired UserDetailRepository userDetailRepository,
                                     @Autowired UserStateRepository userStateRepository,
                                     @Autowired UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.designationRepository = designationRepository;
        this.userDetailRepository = userDetailRepository;
        this.userStateRepository = userStateRepository;
        this.userTypeRepository = userTypeRepository;
    }
}
