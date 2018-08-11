package com.synesis.bcc.structure.database.repositories;

import com.synesis.bcc.structure.database.entities.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignationRepository extends JpaRepository<Designation, Integer> {
}
