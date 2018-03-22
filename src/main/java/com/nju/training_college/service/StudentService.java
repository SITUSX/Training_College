package com.nju.training_college.service;


import com.nju.training_college.util.ResponseData;
import com.nju.training_college.vo.LoginVO;
import com.nju.training_college.vo.SignupVO;
import com.nju.training_college.vo.StdInfoVO;

public interface StudentService {
    ResponseData login(LoginVO vo);

    ResponseData signup(SignupVO vo);

    boolean stopMember(int studentId);

    boolean change(SignupVO vo);

    int findIdByMail(String mail);

    StdInfoVO getInfo(int studentId);
}
