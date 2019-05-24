package com.newscred.omdb.genre.database.repositories;

import com.newscred.omdb.genre.database.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
}
