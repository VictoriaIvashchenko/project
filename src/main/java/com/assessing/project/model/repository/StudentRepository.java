package com.assessing.project.model.repository;

import com.assessing.project.model.entity.Group;
import com.assessing.project.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    ArrayList<Student> findStudentsByGroup(Group group);
//    ArrayList<Student> findStudentsBy
}
