package com.synesis.bcc.structure.database.repositories;

import com.synesis.bcc.structure.database.entities.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
}
