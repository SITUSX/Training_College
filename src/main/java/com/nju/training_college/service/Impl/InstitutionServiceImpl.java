package com.nju.training_college.service.Impl;

import com.nju.training_college.dao.InstitutionDao;
import com.nju.training_college.dao.ModifyInstitutionDao;
import com.nju.training_college.dao.NewInstitutionDao;
import com.nju.training_college.model.Institution;
import com.nju.training_college.model.ModifyInstitution;
import com.nju.training_college.model.NewInstitution;
import com.nju.training_college.service.InstitutionService;
import com.nju.training_college.util.ResponseData;
import com.nju.training_college.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    NewInstitutionDao newInstitutionDao;

    @Autowired
    ModifyInstitutionDao modifyInstitutionDao;

    @Autowired
    InstitutionDao institutionDao;


    @Override
    public ResponseData verify(InsVerifyVO vo) {
        String password;
        Institution institution;
        ResponseData responseData = new ResponseData<Integer>();
        try{
            institution = institutionDao.findByVerify(vo.getVerify());
            if (institution==null){
                responseData.setResult(false);
                responseData.setMessage("识别码错误，没有该机构");
            }
            else{
                password = institution.getPassword();
                if (vo.getPassword().equals(password)){
                    responseData.setResult(true);
                }
                else{
                    responseData.setResult(false);
                    responseData.setMessage("密码错误");
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
    public InsInfoVO getInfo(int verify) {
        try{
            Institution institution = institutionDao.findByVerify(verify);
            return new InsInfoVO(institution.getInstitutionId(), institution.getVerify(), institution.getMail(),
                    institution.getInstitutionName(), institution.getLocation(), institution.getTeachers(), institution.getPhone());
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean apply(NewInsVO vo) {
        try{
            if (newInstitutionDao.findByMail(vo.getMail())!=null)
                return false;

            NewInstitution newInstitution = new NewInstitution(vo.getMail(), vo.getInstitutionName(), vo.getLocation(),
                    vo.getTeachers(), vo.getPhone());

            newInstitutionDao.save(newInstitution);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modify(ModifyInsVO vo, int verify) {
        try{
            ModifyInstitution modifyInstitution = new ModifyInstitution(vo.getInstitutionId(), verify, vo.getPassword(), vo.getMail(),
                    vo.getInstitutionName(), vo.getLocation(),vo.getTeachers(),vo.getPhone());

            modifyInstitutionDao.save(modifyInstitution);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<NewInsVO> getNews() {
        try{
            List<NewInstitution> newInstitutions = newInstitutionDao.findAll();
            List<NewInsVO> list = new ArrayList<>();

            for (NewInstitution newInstitution: newInstitutions) {
                list.add(new NewInsVO(newInstitution.getInstitutionName(), newInstitution.getMail(),
                        newInstitution.getLocation(), newInstitution.getTeachers(), newInstitution.getPhone()));
            }

            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ModifyInsVO> getModify() {
        try{
            List<ModifyInstitution> modifyInstitutions = modifyInstitutionDao.findAll();

            List<ModifyInsVO> list = new ArrayList<>();

            for (ModifyInstitution modifyInstitution: modifyInstitutions) {
                list.add(new ModifyInsVO(modifyInstitution.getInstitutionId(), modifyInstitution.getPassword(), modifyInstitution.getMail(),
                        modifyInstitution.getInstitutionName(), modifyInstitution.getLocation(), modifyInstitution.getTeachers(),
                        modifyInstitution.getPhone()));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
