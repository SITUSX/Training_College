package com.nju.training_college.service;

import com.nju.training_college.vo.LessonBalanceVO;

import java.util.List;

public interface BalanceService {

    boolean settleAccount();

    List<LessonBalanceVO> getBalance(int verify);

    Integer getBalance();

}
