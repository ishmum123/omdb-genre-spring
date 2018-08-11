package com.synesis.bcc.structure.database.repositories;

import com.synesis.bcc.structure.database.entities.UserState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStateRepository extends JpaRepository<UserState, Integer> {
}
