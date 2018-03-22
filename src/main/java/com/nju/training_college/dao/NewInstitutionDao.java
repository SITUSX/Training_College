package com.nju.training_college.dao;

import com.nju.training_college.model.NewInstitution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewInstitutionDao extends JpaRepository<NewInstitution, Integer> {

    NewInstitution findByMail(String mail);
}
