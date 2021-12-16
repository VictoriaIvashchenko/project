package com.assessing.project.model.repository;

import com.assessing.project.model.entity.Mark;
import com.assessing.project.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
    ArrayList<Mark> findMarksByStudent(Student student);
}
