package com.nju.training_college.controller;

import com.nju.training_college.service.LessonService;
import com.nju.training_college.service.MailService;
import com.nju.training_college.service.OrderService;
import com.nju.training_college.service.StudentService;
import com.nju.training_college.util.CookieUtil;
import com.nju.training_college.util.ResponseData;
import com.nju.training_college.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    MailService mailService;

    @Autowired
    LessonService lessonService;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(LoginVO vo, HttpServletResponse response){
        ResponseData responseData = studentService.login(vo);

        if(responseData.getResult()){
            Cookie cookie = new Cookie(CookieUtil.getStudentCookieName(), responseData.getData().toString());
            cookie.setMaxAge(1000);
            response.addCookie(cookie);
        }

        return responseData;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseData logout(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie: cookies) {
            if (cookie.getName().equals(CookieUtil.getStudentCookieName()))
                cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return new ResponseData(true);
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseData verify(String mail){
        int verifyCode = (int)(Math.random()*9000 + 1000);

        mailService.sendSimpleMail(mail, "Verify the email", "Your verify code is "+ verifyCode +".");

        return new ResponseData<Integer>(true, null, verifyCode);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseData signup(SignupVO vo){
        ResponseData responseData = studentService.signup(vo);
        if (responseData.getResult()){
            int studentId = studentService.findIdByMail(vo.getMail());
            mailService.sendSimpleMail(vo.getMail(),"Welcome to Training College", "Your Student ID is "+ studentId +".");
        }
        return responseData;
    }

    @RequestMapping(value = "/stopMember", method = RequestMethod.GET)
    public ResponseData stopMember(@CookieValue(name = "STDID") Cookie cookie){
        boolean result = studentService.stopMember(Integer.parseInt(cookie.getValue()));
        return new ResponseData(result);
    }

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public ResponseData change(SignupVO vo){
        boolean result = studentService.change(vo);
        return new ResponseData(result);
    }

    @RequestMapping(value = "/getLessons", method = RequestMethod.GET)
    public ResponseData<List<SimpleLessonVO>> getLessons(){
        List<SimpleLessonVO> list = lessonService.getLessons();
        if (list == null){
            return new ResponseData<>(false);
        }
        return new ResponseData<>(true, null, list);
    }

    @RequestMapping(value = "/getLesson",method = RequestMethod.GET)
    public ResponseData<LessonVO> getLesson(int lessonId){
        LessonVO vo = lessonService.getLesson(lessonId);
        if(vo==null)
            return new ResponseData<>(false);

        return new ResponseData<>(true, null, vo);
    }

    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
    public ResponseData reserve(NewOrderVO vo, @CookieValue(name = "STDID")Cookie cookie){
        int studentId = Integer.parseInt(cookie.getValue());

        vo.setStudentId(studentId);

        return orderService.reserve(vo);
    }

    @RequestMapping(value = "/getOrders", method = RequestMethod.GET)
    public ResponseData<List<OrderVO>> getOrders(@CookieValue(name = "STDID")Cookie cookie){
        int studentId = Integer.parseInt(cookie.getValue());

        List<OrderVO> list = orderService.getOrders(studentId);
        if(list==null)
            return new ResponseData<>(false);

        return new ResponseData<>(true, null, list);
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public ResponseData pay(int orderId){

        return orderService.pay(orderId);
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public ResponseData cancel(int orderId){
        boolean result = orderService.cancel(orderId);

        if (!result)
            return new ResponseData(false);
        return new ResponseData(true);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    public ResponseData drop(int orderId){

        return orderService.drop(orderId);
    }

    @RequestMapping(value = "/getReturn",method = RequestMethod.POST)
    public ResponseData<Integer> getReturn(int orderId){
        Integer returnPrice = orderService.getReturn(orderId);
        if (returnPrice==null)
            return new ResponseData(false,"错误", null);

        return new ResponseData(true, null, returnPrice);
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    public ResponseData<StdInfoVO> getInfo(@CookieValue(name = "STDID") Cookie cookie) {
        int studentId = Integer.parseInt(cookie.getValue());

        StdInfoVO vo = studentService.getInfo(studentId);
        if (vo!=null)
            return new ResponseData<>(true, null, vo);
        else
            return new ResponseData<>(false);
    }

    @RequestMapping(value = "/getClass", method = RequestMethod.GET)
    public ResponseData<int[]> getClass(int lessonId){
        int[] num = lessonService.getClass(lessonId);

        String stdNumPerClass = Integer.toString(lessonService.getLesson(lessonId).getStdNumPerClass());

        if (num==null)
            return new ResponseData<>(false);

        return new ResponseData<>(true, stdNumPerClass, num);
    }

//    @RequestMapping(value = "/getClass", method = RequestMethod.GET)
//    public ResponseData<int[]> getClass(int lessonId){
//        int[] num = new int[]{27, 28};
//
//        return new ResponseData<>(true, "30", num);
//    }

}
