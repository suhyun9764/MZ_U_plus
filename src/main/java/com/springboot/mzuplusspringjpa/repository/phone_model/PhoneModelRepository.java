package com.springboot.mzuplusspringjpa.repository.phone_model;

import com.springboot.mzuplusspringjpa.entity.PhoneModel;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneModelRepository extends JpaRepository<PhoneModel, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<PhoneModel> findById(int phoneModelId);

}
