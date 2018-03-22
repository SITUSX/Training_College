package com.nju.training_college.dao;

import com.nju.training_college.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, Integer> {

    Student findByMail(String mail);
}