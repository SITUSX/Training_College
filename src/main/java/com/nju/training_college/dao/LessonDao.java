package com.nju.training_college.dao;

import com.nju.training_college.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface LessonDao extends JpaRepository<Lesson, Integer> {

    List<Lesson> findAllByVerify(int verify);

    @Query("select l.lessonName from Lesson l where l.lessonId = ?1")
    String findLessonNameByLessonId(int lessonId);

    @Query("select l.startDay from Lesson l where l.lessonId = ?1")
    Date findStartDayByLessonId(int lessonId);

    @Query("select l.lessonId from Lesson l where l.startDay < ?1")
    List<Integer> findLessonIdByStartDayBefore(Date now);
}
