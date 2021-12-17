package com.assessing.project.model.repository;

import com.assessing.project.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    @Query("select g.name from Group as g inner join g.subjects as s on s = :#{#subject} and :#{#subject.teacher} = :#{#teacher}")
    ArrayList<String> findGroupByTeacher(@Param("teacher") Teacher teacher, @Param("subject") Subject subject);

    @Query("select g.faculty.name from Group as g inner join g.subjects as s on s = :#{#subject} and :#{#subject.teacher} = :#{#teacher}")
    ArrayList<String> findFacultyByTeacherGroup(@Param("teacher") Teacher teacher, @Param("subject") Subject subject);

    @Query("select g.name from Group as g")
    ArrayList<String> findGroupName();

    Group findGroupByName(String name);

    ArrayList<Group> findGroupsByFaculty(Faculty faculty);

    @Query("select distinct g.name from Group as g where :#{#student.group} = g")
    String findGroupByStudent(@Param("student") Student student);

}
