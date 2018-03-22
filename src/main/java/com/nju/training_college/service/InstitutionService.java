package com.nju.training_college.service;

import com.nju.training_college.util.ResponseData;
import com.nju.training_college.vo.*;

import java.util.List;

public interface InstitutionService {

    ResponseData verify(InsVerifyVO vo);

    InsInfoVO getInfo(int verify);

    boolean apply(NewInsVO vo);

    boolean modify(ModifyInsVO vo, int verify);

    List<NewInsVO> getNews();

    List<ModifyInsVO> getModify();

}
