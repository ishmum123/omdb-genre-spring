package com.newscred.omdb.genre.database.repositories;

import com.newscred.omdb.genre.database.entities.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignationRepository extends JpaRepository<Designation, Integer> {
}
