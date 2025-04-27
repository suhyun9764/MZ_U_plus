package com.springboot.mzuplusspringjpa.repository;

import com.springboot.mzuplusspringjpa.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    List<Role> findRoleByName(String name);
}
