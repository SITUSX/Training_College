package com.nju.training_college.util;

import com.nju.training_college.service.BalanceService;
import com.nju.training_college.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedule {
    @Autowired
    OrderService orderService;

    @Autowired
    BalanceService balanceService;

    @Scheduled(cron = "0 0/1 * * * ?")
    private void handle(){
        System.out.println(DateUtil.getCurrentUtilDate()+"：处理未支付订单");
        orderService.handle();
    }

//    @Scheduled(cron = "0/5 * * * * ?")
//    private void test(){
//        balanceService.settleAccount();
//    }
}
