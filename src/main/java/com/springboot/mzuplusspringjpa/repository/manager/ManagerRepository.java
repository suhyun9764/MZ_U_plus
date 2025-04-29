package com.springboot.mzuplusspringjpa.repository.manager;

import com.springboot.mzuplusspringjpa.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {

    @Query("SELECT m from Manager m join fetch m.roles")
//    @EntityGraph(attributePaths = "roles")
    Optional<Manager> findByEmail(String email);

    boolean existsByEmail(String email);
}
