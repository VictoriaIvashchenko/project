package com.assessing.project.model.repository;

import com.assessing.project.model.entity.Mark;
import com.assessing.project.model.entity.Student;
import com.assessing.project.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
    ArrayList<Mark> findMarksByStudentAndSemester(Student student, Integer semester);
    @Query("select sum(m.value) from Mark m where m.student = #{#student} and m.semester = #{#semester}")
    Integer findSumOfMark(@Param("student") Student student, @Param("semester") Integer semester);

    @Query("select count(m.value) from Mark m where m.student = #{#student} and m.semester = #{#semester}")
    Integer findCountOfMark(@Param("student") Student student, @Param("semester") Integer semester);

    Mark findMarkByStudentAndSubject(Student student, Subject subject);


}
