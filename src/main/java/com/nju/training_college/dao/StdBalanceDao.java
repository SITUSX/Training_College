package com.nju.training_college.dao;

import com.nju.training_college.model.StdBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StdBalanceDao extends JpaRepository<StdBalance, Integer> {
}
