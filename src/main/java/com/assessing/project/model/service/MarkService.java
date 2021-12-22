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
    public Integer findMarkByStudentAndSubject(Student student, Subject subject){
        Mark mark = markRepository.findMarkByStudentAndSubject(student, subject);
        if(mark == null){
            return 0;
        } else{
            return mark.getValue();
        }
    }
}
