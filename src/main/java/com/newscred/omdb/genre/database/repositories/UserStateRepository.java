package com.newscred.omdb.genre.database.repositories;

import com.newscred.omdb.genre.database.entities.UserState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStateRepository extends JpaRepository<UserState, Integer> {
}
