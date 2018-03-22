package com.nju.training_college.dao;

import com.nju.training_college.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BalanceDao extends JpaRepository<Balance, Integer> {
}
