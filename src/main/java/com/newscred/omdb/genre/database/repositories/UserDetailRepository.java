package com.newscred.omdb.genre.database.repositories;

import com.newscred.omdb.genre.database.entities.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
}
