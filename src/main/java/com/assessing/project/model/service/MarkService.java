package com.assessing.project.model.service;

import com.assessing.project.model.entity.Mark;
import com.assessing.project.model.entity.Student;
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

    public ArrayList<Mark> findByStudent(Student student){return markRepository.findMarksByStudent(student);}
    public Integer findMarkValue(Mark mark){
        return mark.getValue();
    }
}
