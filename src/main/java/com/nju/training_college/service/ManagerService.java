package com.nju.training_college.service;

import com.nju.training_college.vo.InsInfoVO;
import com.nju.training_college.vo.StdInfoVO;

import java.util.List;

public interface ManagerService {
    boolean confirmNewInstitution(String mail);

    boolean confirmModifyInstitution(int institutionId);

    List<StdInfoVO> getStudents();

    List<InsInfoVO> getInstitutions();
}
