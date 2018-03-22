package com.nju.training_college.service.Impl;

import com.nju.training_college.dao.StudentDao;
import com.nju.training_college.model.Student;
import com.nju.training_college.service.StudentService;
import com.nju.training_college.util.ResponseData;
import com.nju.training_college.vo.LoginVO;
import com.nju.training_college.vo.SignupVO;
import com.nju.training_college.vo.StdInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;

    @Override
    public ResponseData login(LoginVO vo){
        String password;
        Student student;
        ResponseData responseData = new ResponseData<Integer>();
        try{
            student = studentDao.findByMail(vo.getMail());
            if (student==null){
                responseData.setResult(false);
                responseData.setMessage("There is No Such Student!");
            }
            else{
                password = student.getPassword();
                if (vo.getPassword().equals(password)){
                    responseData.setResult(true);
                    responseData.setData(student.getStudentId());
                }
                else{
                    responseData.setResult(false);
                    responseData.setMessage("Password Wrong!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            responseData.setResult(false);
            responseData.setMessage("Something wrong!");
        }
        return responseData;
    }

    @Override
    public ResponseData signup(SignupVO vo) {
        ResponseData responseData = new ResponseData();
        try{
            if (studentDao.findByMail(vo.getMail())!=null){
                responseData.setResult(false);
                responseData.setMessage("Student Exists!");
            }else{
                Student student = new Student(vo.getMail(), vo.getPassword(), vo.getStdName());
                studentDao.save(student);
                responseData.setResult(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setResult(false);
            responseData.setMessage("Something wrong!");
        }
        return responseData;
    }

    @Override
    public boolean stopMember(int studentId) {
        try{
            Student student = studentDao.getOne(studentId);
            student.setMember(false);
            studentDao.save(student);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean change(SignupVO vo) {
        try {
            Student student = studentDao.findByMail(vo.getMail());
            student.setStdName(vo.getStdName());
            student.setPassword(vo.getPassword());
            studentDao.save(student);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int findIdByMail(String mail) {
        try{
            return studentDao.findByMail(mail).getStudentId();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public StdInfoVO getInfo(int studentId) {
        try{
            Student student = studentDao.getOne(studentId);
            return new StdInfoVO(student.getStudentId(), student.getMail(), student.getStdName(),
                    student.isMember(), student.getLvMember(), student.getConsumption());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
