package com.assessing.project.model.service;

import com.assessing.project.model.entity.Mark;
import com.assessing.project.model.entity.Student;
import com.assessing.project.model.entity.Subject;
import com.assessing.project.model.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class MarkService {
    private MarkRepository markRepository;
    @Autowired
    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public ArrayList<Mark> findByStudentAndSemester(Student student, Integer semester){return markRepository.findMarksByStudentAndSemester(student, semester);}
    public Integer findMarkValue(Mark mark){
        return mark.getValue();
    }
    public Double findAverageMark(Student student, Integer semester){
        return (double)markRepository.findSumOfMark(student, semester) / markRepository.findCountOfMark(student, semester);
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
