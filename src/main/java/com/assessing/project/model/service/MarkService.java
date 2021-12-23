package com.assessing.project.model.service;

import com.assessing.project.model.entity.*;
import com.assessing.project.model.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class MarkService {
    private MarkRepository markRepository;
    @Autowired
    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }
    public ArrayList<Mark> findMarksBySubject(Subject subject){
        return markRepository.findMarksBySubject(subject);
    }
    public ArrayList<Mark> findMarksByGroupAndTeacher(Group group, Teacher teacher){
        return markRepository.findMarksByGroupAndTeacher(group, teacher);
    }
    public Mark findMarkByStudentAndTeacher(Student student, Teacher teacher){
        return markRepository.findMarkByStudentAndTeacher(student, teacher);
    }
    public Mark findMarkByStudentAndSubjectAndSemester(Student student, Subject subject, Integer semester){
        return markRepository.findMarkByStudentAndSubjectAndSemester(student, subject, semester);
    }
    Mark findMarkByStudentAndTeacherAndSemester(Student student, Teacher teacher, Integer semester){
        return markRepository.findMarkByStudentAndTeacherAndSemester(student, teacher, semester);
    }
    public Integer markGetIntegerValue(Mark mark){
        if(mark == null){
            return 0;
        } else{
            return mark.getValue();
        }
    }
    public ArrayList<Mark> findByStudentAndSemester(Student student, Integer semester){return markRepository.findMarksByStudentAndSemester(student, semester);}
    public Integer findMarkValue(Mark mark){
        return mark.getValue();
    }
    public Double findAverageMark(Student student, Integer semester){
        Integer count = markRepository.findCountOfMark(student, semester);
        if(count != 0){
            return (double)markRepository.findSumOfMark(student, semester) / count;
        }else {
            return 0.0;
        }
    }
    public Mark findMarkByStudentAndSubject(Student student, Subject subject){
        return markRepository.findMarkByStudentAndSubject(student, subject);
    }
    public void create(Student student, Subject subject, Integer value, Integer semester){
        Mark mark = new Mark(student, subject, value, semester);
        markRepository.save(mark);
    }
    public void update(Mark mark){
        markRepository.save(mark);
    }
}
