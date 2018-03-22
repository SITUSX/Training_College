package com.nju.training_college.dao;

import com.nju.training_college.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDao extends JpaRepository<Order, Integer> {

    @Query("select sum(o.studentNum) from Order o where o.lessonId = ?1 and o.classId = ?2")
    Integer findStdNum(int lessonId, int classId);

    List<Order> findAllByStudentId(int studentId);

    List<Order> findAllByPaidAndCancel(boolean paid, boolean cancel);

    @Query("select sum(o.actualPrice) from Order o where o.lessonId = ?1 and o.settled = false ")
    Integer findBalance(int lessonId);

    @Modifying
    @Query("update Order o set o.settled = true where o.lessonId = ?1")
    void setSettled(int lessonId);
}
