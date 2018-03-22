package com.nju.training_college.controller;

import com.nju.training_college.service.BalanceService;
import com.nju.training_college.service.InstitutionService;
import com.nju.training_college.service.MailService;
import com.nju.training_college.service.ManagerService;
import com.nju.training_college.util.Manager;
import com.nju.training_college.util.ResponseData;
import com.nju.training_college.vo.InsInfoVO;
import com.nju.training_college.vo.ModifyInsVO;
import com.nju.training_college.vo.NewInsVO;
import com.nju.training_college.vo.StdInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @Autowired
    InstitutionService institutionService;

    @Autowired
    BalanceService balanceService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(String id, String password){
        if (!id.equals(Manager.getManagerId()))
            return new ResponseData(false, "ID错误", null);
        if (!password.equals(Manager.getManagerPassword()))
            return new ResponseData(false, "密码错误", null);
        return new ResponseData(true);
    }

    @RequestMapping(value = "/getNews", method = RequestMethod.GET)
    public ResponseData<List<NewInsVO>> getNews(){
        List<NewInsVO> list = institutionService.getNews();

        if (list == null)
            return new ResponseData<>(false);

        if (list.size() == 0){
            return new ResponseData(true, "没有新机构申请", null);
        }
        return new ResponseData<>(true, null, list);
    }

    @RequestMapping(value = "/getModify", method = RequestMethod.GET)
    public ResponseData<List<ModifyInsVO>> getModify(){
        List<ModifyInsVO> list = institutionService.getModify();

        if (list == null)
            return new ResponseData<>(false);

        if (list.size() == 0){
            return new ResponseData(true, "没有机构信息修改申请", null);
        }
        return new ResponseData<>(true, null, list);
    }

    @RequestMapping(value = "/confirmNewInstitution", method = RequestMethod.POST)
    public ResponseData confirmNewInstitution(String mail){
        boolean result = managerService.confirmNewInstitution(mail);
        return new ResponseData(result);
    }

    @RequestMapping(value = "/confirmModifyInstitution", method = RequestMethod.POST)
    public ResponseData confirmModifyInstitution(int institutionId){
        boolean result = managerService.confirmModifyInstitution(institutionId);
        return new ResponseData(result);
    }

    @RequestMapping(value = "/settleAccounts", method =RequestMethod.GET)
    public ResponseData settleAccounts(){
        boolean result = balanceService.settleAccount();

        if (!result)
            return new ResponseData(false);
        return new ResponseData(true);
    }

    @RequestMapping(value = "/getBalance", method =RequestMethod.GET)
    public ResponseData getBalance(){
        Integer balance = balanceService.getBalance();

        if (balance==null)
            return new ResponseData(false);

        return new ResponseData(true, balance.toString());
    }

    @RequestMapping(value = "/getStudents", method =RequestMethod.GET)
    public ResponseData<List<StdInfoVO>> getStudents(){
        List<StdInfoVO> list = managerService.getStudents();

        if (list==null)
            return new ResponseData(false, "获取错误");
        if(list.size()==0)
            return new ResponseData(false, "没有学生");

        return new ResponseData(true, null, list);
    }

    @RequestMapping(value = "/getInstitutions", method =RequestMethod.GET)
    public ResponseData<List<InsInfoVO>> getInstitutions(){
        List<InsInfoVO> list = managerService.getInstitutions();

        if (list==null)
            return new ResponseData(false, "获取错误");
        if(list.size()==0)
            return new ResponseData(false, "没有机构");

        return new ResponseData(true, null, list);
    }
}
