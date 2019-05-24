package com.newscred.omdb.genre.helpers.components;

import com.newscred.omdb.genre.database.repositories.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Getter
@Component
public class RepositoryHolderComponent {
    private final UserRepository userRepository;
    private final DesignationRepository designationRepository;
    private final UserDetailRepository userDetailRepository;
    private final UserStateRepository userStateRepository;
    private final UserTypeRepository userTypeRepository;
}
