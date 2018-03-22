package com.nju.training_college.service.Impl;

import com.nju.training_college.dao.*;
import com.nju.training_college.model.*;
import com.nju.training_college.service.MailService;
import com.nju.training_college.service.ManagerService;
import com.nju.training_college.vo.InsInfoVO;
import com.nju.training_college.vo.StdInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    MailService mailService;

    @Autowired
    InstitutionDao institutionDao;

    @Autowired
    NewInstitutionDao newInstitutionDao;

    @Autowired
    ModifyInstitutionDao modifyInstitutionDao;

    @Autowired
    StudentDao studentDao;


    @Override
    public boolean confirmNewInstitution(String mail) {
        try{
            NewInstitution newInstitution = newInstitutionDao.findByMail(mail);

            Institution institution = new Institution();

            institution.setInstitutionName(newInstitution.getInstitutionName());
            institution.setMail(newInstitution.getMail());
            institution.setLocation(newInstitution.getLocation());
            institution.setTeachers(newInstitution.getTeachers());
            institution.setPhone(newInstitution.getPhone());

            int verify = (int)(Math.random()*9000000+1000000);
            while (institutionDao.findByVerify(verify)!=null)
                verify = verify+1;
            institution.setVerify(verify);

            String password = Integer.toString((int)(Math.random()*90000000+10000000));
            institution.setPassword(password);

            institutionDao.save(institution);

            mailService.sendSimpleMail(newInstitution.getMail(), "新机构确认",
                    "新机构 "+ newInstitution.getInstitutionName() +" 申请成功。\n" +
                            "识别码为"+ verify +"，密码为"+ password +"。");

            newInstitutionDao.delete(newInstitution);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean confirmModifyInstitution(int institutionId) {
        try{
            ModifyInstitution modifyInstitution = modifyInstitutionDao.findByInstitutionId(institutionId);

            Institution institution = institutionDao.getOne(institutionId);

            institution.setPassword(modifyInstitution.getPassword());
            institution.setInstitutionName(modifyInstitution.getInstitutionName());
            institution.setLocation(modifyInstitution.getLocation());
            institution.setTeachers(modifyInstitution.getTeachers());
            institution.setPhone(modifyInstitution.getPhone());

            institutionDao.save(institution);

            mailService.sendSimpleMail(modifyInstitution.getMail(), "Modify Institution Confirmed",
                    "The institution "+ institutionId +"'s Information is changed success!");

            modifyInstitutionDao.delete(modifyInstitution);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<StdInfoVO> getStudents() {
        try{
            List<Student> students = studentDao.findAll();
            List<StdInfoVO> list = new ArrayList<>();

            for (Student student: students) {
                list.add(new StdInfoVO(student.getStudentId(), student.getMail(), student.getStdName(),
                        student.isMember(),student.getLvMember(), student.getConsumption()));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<InsInfoVO> getInstitutions() {
        try{
            List<Institution> institutions = institutionDao.findAll();
            List<InsInfoVO> list = new ArrayList<>();

            for (Institution institution: institutions) {
                list.add(new InsInfoVO(institution.getInstitutionId(), institution.getVerify(), institution.getMail(),
                        institution.getInstitutionName(), institution.getLocation(), institution.getTeachers(),institution.getPhone()));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
