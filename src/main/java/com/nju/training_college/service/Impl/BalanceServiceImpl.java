package com.nju.training_college.service.Impl;

import com.nju.training_college.dao.BalanceDao;
import com.nju.training_college.dao.InsBalanceDao;
import com.nju.training_college.dao.LessonDao;
import com.nju.training_college.dao.OrderDao;
import com.nju.training_college.model.Balance;
import com.nju.training_college.model.InsBalance;
import com.nju.training_college.service.BalanceService;
import com.nju.training_college.util.DateUtil;
import com.nju.training_college.vo.LessonBalanceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    InsBalanceDao insBalanceDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    LessonDao lessonDao;

    @Autowired
    BalanceDao balanceDao;

    @Override
    public boolean settleAccount() {
        try {
            //已开课的课程ID
            List<Integer> lessonIds = lessonDao.findLessonIdByStartDayBefore(DateUtil.getCurrentSQLDate());
            InsBalance insBalance;
            Integer lessonBalance;
            int verify;

            Balance balance = balanceDao.getOne(1);

            for (int lessonId :lessonIds) {
                lessonBalance = orderDao.findBalance(lessonId);
                if(lessonBalance!=null){
                    verify = lessonDao.getOne(lessonId).getVerify();
                    insBalance = new InsBalance();
                    insBalance.setVerify(verify);
                    insBalance.setLessonId(lessonId);
                    insBalance.setBalance(lessonBalance/2);
                    insBalanceDao.save(insBalance);

                    //设置订单已结算
                    orderDao.setSettled(lessonId);

                    //TrainingCollege系统资产
                    balance.setBalance(balance.getBalance() + lessonBalance/2);
                    balanceDao.save(balance);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<LessonBalanceVO> getBalance(int verify) {
        try {
            List<InsBalance> balances = insBalanceDao.findAllByVerify(verify);
            List<LessonBalanceVO> list = new ArrayList<>();

            for (InsBalance insBalance: balances) {
                list.add(new LessonBalanceVO(insBalance.getLessonId(), insBalance.getBalance()));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer getBalance() {
        try {
            return balanceDao.getOne(1).getBalance();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
