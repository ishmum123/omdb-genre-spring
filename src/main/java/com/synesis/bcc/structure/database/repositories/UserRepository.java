package com.synesis.bcc.structure.database.repositories;

import com.synesis.bcc.structure.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Iterable<User> findByFirstname(String firstname);
    Iterable<User> findByLastname(String lastname);
    @Query("SELECT u FROM User u WHERE firstname LIKE CONCAT('%',:name,'%') OR middlename LIKE CONCAT('%',:name,'%') OR lastname LIKE CONCAT('%',:name,'%')")
    Iterable<User> findByName(@Param("name") String name);
}
