package com.nju.training_college.service;

import com.nju.training_college.vo.LessonVO;
import com.nju.training_college.vo.SimpleLessonVO;

import java.util.List;
import java.util.Map;

public interface LessonService {
    boolean addLesson(LessonVO vo, int verify);

    List<SimpleLessonVO> getLessons();

    LessonVO getLesson(int lessonId);

    List<LessonVO> getLessons(int verify);

    int[] getClass(int lessonId);
}
