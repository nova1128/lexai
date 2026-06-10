package com.lexai.lexaibackend.repository;

import com.lexai.lexaibackend.model.LawyerProfile;
import com.lexai.lexaibackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawyerProfileRepository extends JpaRepository<LawyerProfile, Long> {

    List<LawyerProfile> findBySpecialization(String specialization);

    List<LawyerProfile> findByAvailableTrue();

    List<LawyerProfile> findBySpecializationAndAvailableTrue(String specialization);
}
