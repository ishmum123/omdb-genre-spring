package com.synesis.bcc.structure.database.repositories;

import com.synesis.bcc.structure.database.entities.User;
import com.synesis.bcc.structure.database.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
}
