package com.assessing.project.model.repository;

import com.assessing.project.model.entity.Faculty;
import com.assessing.project.model.entity.Group;
import com.assessing.project.model.entity.Subject;
import com.assessing.project.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    @Query("select g.name from Group as g inner join g.subjects as s on s = :#{#subject} and :#{#subject.teacher} = :#{#teacher}")
    ArrayList<String> findGroupByTeacher(@Param("teacher") Teacher teacher, @Param("subject") Subject subject);

    @Query("select g.faculty.name from Group as g inner join g.subjects as s on s = :#{#subject} and :#{#subject.teacher} = :#{#teacher}")
    ArrayList<String> findFacultyByTeacherGroup(@Param("teacher") Teacher teacher, @Param("subject") Subject subject);

}
