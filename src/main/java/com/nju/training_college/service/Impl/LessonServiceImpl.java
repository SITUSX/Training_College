package com.nju.training_college.service.Impl;

import com.nju.training_college.dao.LessonDao;
import com.nju.training_college.dao.OrderDao;
import com.nju.training_college.model.Lesson;
import com.nju.training_college.service.LessonService;
import com.nju.training_college.util.DateUtil;
import com.nju.training_college.vo.LessonVO;
import com.nju.training_college.vo.SimpleLessonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    LessonDao lessonDao;

    @Autowired
    OrderDao orderDao;

    @Override
    public boolean addLesson(LessonVO vo, int verify) {
        try {

            Date startDay = DateUtil.format(vo.getStartDay());
            Date endDay = DateUtil.format(vo.getEndDay());

            Lesson lesson = new Lesson(verify, vo.getLessonName(), startDay, endDay,
                    vo.getLessonTime(), vo.getTeachers(), vo.getClassNum(), vo.getStdNumPerClass(),
                    vo.getPrice(),vo.getType(), vo.getDescription());

            lessonDao.save(lesson);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SimpleLessonVO> getLessons() {
        try{
            List<Lesson> lessons = lessonDao.findAll();
            List<SimpleLessonVO> list = new ArrayList<>();
            for (Lesson lesson: lessons) {
                list.add(new SimpleLessonVO(lesson.getLessonId(), lesson.getLessonName(),
                        lesson.getDescription()));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public LessonVO getLesson(int lessonId) {
        try{
            Lesson lesson = lessonDao.getOne(lessonId);

            String startDay = DateUtil.format(lesson.getStartDay());
            String endDay = DateUtil.format(lesson.getEndDay());

            return new LessonVO(lesson.getLessonName(), startDay, endDay,
                    lesson.getLessonTime(), lesson.getTeachers(), lesson.getClassNum(), lesson.getStdNumPerClass(),
                    lesson.getPrice(), lesson.getType(), lesson.getDescription());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<LessonVO> getLessons(int verify) {
        try{
            List<Lesson> lessons = lessonDao.findAllByVerify(verify);

            List<LessonVO> list = new ArrayList<>();
            for (Lesson lesson: lessons) {
                list.add(new LessonVO(lesson.getLessonName(), DateUtil.format(lesson.getStartDay()),
                        DateUtil.format(lesson.getEndDay()), lesson.getLessonTime(), lesson.getTeachers(),
                        lesson.getClassNum(), lesson.getStdNumPerClass(), lesson.getPrice(), lesson.getType(), lesson.getDescription()));
            }

            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int[] getClass(int lessonId) {
        try {
            int classNum = lessonDao.getOne(lessonId).getClassNum();

            int[] num = new int[classNum];
            Integer stdNum;

            for (int i = 0; i < classNum; i++) {
                stdNum = orderDao.findStdNum(lessonId, i+1);
                if (stdNum==null)
                    num[i] = 0;
                else {
                    num[i] = stdNum;
                }
            }
            return num;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
