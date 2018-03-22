package com.nju.training_college.dao;

import com.nju.training_college.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionDao extends JpaRepository<Institution, Integer> {

    Institution findByVerify(int verify);

}
