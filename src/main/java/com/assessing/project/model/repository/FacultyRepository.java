package com.assessing.project.model.repository;

import com.assessing.project.model.entity.Faculty;
import com.assessing.project.model.entity.Subject;
import com.assessing.project.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
//    @Query("select f from Faculty as f inner join as g on g.faculty = f inner join ")
//    ArrayList<Faculty>findFacultyByTeacher(@Param("teacher")Teacher teacher, @Param("subject")Subject subject)
    @Query("select f.name from Faculty as f")
    ArrayList<String> findFacultyName();
    Faculty findFacultyByName(String name);
}
