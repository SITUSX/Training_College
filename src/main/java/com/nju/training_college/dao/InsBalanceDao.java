package com.nju.training_college.dao;

import com.nju.training_college.model.InsBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsBalanceDao extends JpaRepository<InsBalance, Integer> {

    List<InsBalance> findAllByVerify(int verify);
}
