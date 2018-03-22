package com.nju.training_college.controller;

import com.nju.training_college.service.BalanceService;
import com.nju.training_college.service.InstitutionService;
import com.nju.training_college.service.LessonService;
import com.nju.training_college.util.CookieUtil;
import com.nju.training_college.util.ResponseData;
import com.nju.training_college.vo.*;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/institution")
public class InstitutionController {
    @Autowired
    InstitutionService institutionService;

    @Autowired
    LessonService lessonService;

    @Autowired
    BalanceService balanceService;

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseData verify(InsVerifyVO vo, HttpServletResponse response){
        ResponseData responseData = institutionService.verify(vo);

        if (responseData.getResult()){
            Cookie cookie = new Cookie(CookieUtil.getInstitutionCookieName(), Integer.toString(vo.getVerify()));
            cookie.setMaxAge(1000);
            response.addCookie(cookie);
        }

        return responseData;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseData logout(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie: cookies) {
            if (cookie.getName().equals(CookieUtil.getInstitutionCookieName()))
                cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return new ResponseData(true);
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public ResponseData apply(NewInsVO vo){
        boolean result = institutionService.apply(vo);

        if (!result)
            return new ResponseData(result, "这个邮箱已被使用", null);

        return new ResponseData(result);
    }

    @RequestMapping(value = "/getLessons", method = RequestMethod.GET)
    public ResponseData<List> getLessons(@CookieValue(name = "INSID") Cookie cookie){
        int verify = Integer.parseInt(cookie.getValue());

        List<LessonVO> list = lessonService.getLessons(verify);

        if(list==null)
            return new ResponseData<>(false);

        return new ResponseData<>(true, null, list);

    }

    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public ResponseData modify(ModifyInsVO vo, @CookieValue(name = "INSID")Cookie cookie){
        int verify = Integer.parseInt(cookie.getValue());

        boolean result = institutionService.modify(vo, verify);

        return new ResponseData(result);
    }

    @RequestMapping(value = "/getInfo",method = RequestMethod.GET)
    public ResponseData<InsInfoVO> getInfo(@CookieValue(name = "INSID") Cookie cookie){
        int verify = Integer.parseInt(cookie.getValue());
        InsInfoVO vo = institutionService.getInfo(verify);
        if (vo!=null)
            return new ResponseData<>(true, null, vo);
        return new ResponseData<>(false);
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResponseData publish(LessonVO vo, @CookieValue(name = "INSID") Cookie cookie){
        int verify = Integer.parseInt(cookie.getValue());

        boolean result = lessonService.addLesson(vo, verify);

        return new ResponseData(result);
    }

    @RequestMapping(value = "/getBalance", method = RequestMethod.GET)
    public ResponseData<List<LessonBalanceVO>> getBalance(@CookieValue(name = "INSID") Cookie cookie){
        int verify = Integer.parseInt(cookie.getValue());

        List<LessonBalanceVO> list = balanceService.getBalance(verify);

        if (list==null)
            return new ResponseData<>(false, "获取错误");
        if (list.size()==0)
            return new ResponseData<>(false,"还没有已收入课程");

        return new ResponseData<>(true, null, list);
    }

}
