package com.lexai.lexaibackend.repository;

import com.lexai.lexaibackend.model.LegalQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalQueryRepository extends JpaRepository<LegalQuery, Long> {

}