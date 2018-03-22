package com.nju.training_college.service;

import com.nju.training_college.util.ResponseData;
import com.nju.training_college.vo.NewOrderVO;
import com.nju.training_college.vo.OrderVO;

import java.util.List;

public interface OrderService {
    ResponseData<Integer> reserve(NewOrderVO vo);

    ResponseData pay(int orderId);

    boolean cancel(int orderId);

    void handle();

    ResponseData<Integer> drop(int orderId);

    Integer getReturn(int orderId);

    List<OrderVO> getOrders(int studentId);
}
