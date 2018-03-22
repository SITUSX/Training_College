package com.nju.training_college.service.Impl;

import com.nju.training_college.dao.LessonDao;
import com.nju.training_college.dao.OrderDao;
import com.nju.training_college.dao.StdBalanceDao;
import com.nju.training_college.dao.StudentDao;
import com.nju.training_college.model.Order;
import com.nju.training_college.model.StdBalance;
import com.nju.training_college.model.Student;
import com.nju.training_college.service.BalanceService;
import com.nju.training_college.service.OrderService;
import com.nju.training_college.util.DateUtil;
import com.nju.training_college.util.MemberUtil;
import com.nju.training_college.util.ResponseData;
import com.nju.training_college.vo.NewOrderVO;
import com.nju.training_college.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    StudentDao studentDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    LessonDao lessonDao;

    @Autowired
    StdBalanceDao stdBalanceDao;


    @Override
    public ResponseData<Integer> reserve(NewOrderVO vo) {
        try{
            int lvMember = studentDao.getOne(vo.getStudentId()).getLvMember();
            int discount = MemberUtil.getDiscount(lvMember);

            int price = vo.getStudentNum() * lessonDao.getOne(vo.getLessonId()).getPrice();
            int totalPrice =  price * discount / 100;

            Order order = new Order();
            order.setLessonId(vo.getLessonId());
            order.setStudentId(vo.getStudentId());
            order.setClassId(vo.getClassId());
            order.setStudentNum(vo.getStudentNum());

            order.setPrice(price);
            order.setTotalPrice(totalPrice);

            order.setTime(DateUtil.getCurrent());

            orderDao.save(order);

            return new ResponseData<>(true, Integer.toString(price), totalPrice);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseData<>(false);
        }
    }

    @Override
    public ResponseData pay(int orderId) {
        try{
            Order order = orderDao.getOne(orderId);

            int studentId = order.getStudentId();

            StdBalance stdBalance = stdBalanceDao.getOne(studentId);
            if(stdBalance.getBalance() < order.getTotalPrice()){
                return new ResponseData(false, "余额不足！", null);
            }else {
                order.setPaid(true);

                //学生总消费
                Student student = studentDao.getOne(studentId);
                int consumption = student.getConsumption() + order.getTotalPrice();
                student.setConsumption(consumption);
                int level = MemberUtil.setLevel(consumption);
                if(level>0)
                    student.setMember(true);
                student.setLvMember(level);
                studentDao.save(student);

                //订单实际收到总额
                order.setActualPrice(order.getTotalPrice());
                orderDao.save(order);

                //学生账户余额
                stdBalance.setBalance(stdBalance.getBalance() - order.getTotalPrice());
                stdBalanceDao.save(stdBalance);

                return new ResponseData(true);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseData(false, "错误", null);
        }
    }

    @Override
    public boolean cancel(int orderId) {
        try{
            Order order = orderDao.getOne(orderId);
            order.setCancel(true);
            order.setActualPrice(0);

            orderDao.save(order);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void handle() {
        try{
            List<Order> orders = orderDao.findAllByPaidAndCancel(false, false);
            for (Order order: orders) {
                long makeOrder = order.getTime().getTime();
                long now = System.currentTimeMillis();
                if ((now - makeOrder)/(1000*60)>DateUtil.CANCEL_ORDER){
                    order.setCancel(true);
                    order.setActualPrice(0);
                    orderDao.save(order);
                    System.out.println("处理未支付的订单:"+order.getOrderId());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ResponseData<Integer> drop(int orderId) {
        try{
            Order order = orderDao.getOne(orderId);
            order.setDrop(true);

            Date start = lessonDao.getOne(order.getLessonId()).getStartDay();

            if (DateUtil.getCurrent().compareTo(start) > 0){

                orderDao.save(order);
                return new ResponseData(true, "退课成功！\n已过开课日期，费用不退。",0);
            }else {
                int days = DateUtil.returnPercent(start);
                int returnPrice = order.getTotalPrice()* days /10;

                //学生总消费
                Student student = studentDao.getOne(order.getStudentId());
                student.setConsumption(student.getConsumption() - returnPrice);

                //学生账户余额
                StdBalance stdBalance = stdBalanceDao.getOne(order.getStudentId());
                stdBalance.setBalance(stdBalance.getBalance() + returnPrice);

                //订单实际总额
                order.setActualPrice(order.getTotalPrice() - returnPrice);
                orderDao.save(order);
                return new ResponseData(true, "退课成功！\n距离开课还有"+ days+"天。\n退回费用"+returnPrice+"。", returnPrice);
            }
        }catch (Exception e){
            return new ResponseData(false, "错误", null);
        }
    }

    @Override
    public Integer getReturn(int orderId) {
        try{
            Order order = orderDao.getOne(orderId);
            return order.getTotalPrice() - order.getActualPrice();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OrderVO> getOrders(int studentId) {
        try{
            List<Order> orders = orderDao.findAllByStudentId(studentId);
            List<OrderVO> list = new ArrayList<>();

            String lessonName;

            for (Order order : orders) {
                lessonName = lessonDao.findLessonNameByLessonId(order.getLessonId());
                list.add(new OrderVO(order.getOrderId(), order.getStudentId(), lessonName,
                        order.getClassId(), order.getStudentNum(), order.getTotalPrice(),
                        order.isPaid(), order.isCancel(), order.isDrop()));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
