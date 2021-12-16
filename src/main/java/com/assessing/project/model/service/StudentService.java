package com.assessing.project.model.service;

import com.assessing.project.model.entity.Group;
import com.assessing.project.model.entity.Student;
import com.assessing.project.model.entity.Teacher;
import com.assessing.project.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student findById(Integer id){return studentRepository.getById(id);}
    public ArrayList<Student> findByGroup(Group group){return studentRepository.findStudentsByGroup(group);}
    public String[]findStudentName(Student student){
        String[] value = new String[3];
        value[0] = student.getName();
        value[1] = student.getPatronymic();
        value[2] = student.getSurname();
        return value;
    }

}
