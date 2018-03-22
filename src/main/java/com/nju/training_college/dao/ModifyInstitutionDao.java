package com.nju.training_college.dao;

import com.nju.training_college.model.ModifyInstitution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModifyInstitutionDao extends JpaRepository<ModifyInstitution, Integer> {

    ModifyInstitution findByInstitutionId(int institutionId);
}
