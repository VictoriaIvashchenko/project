package com.assessing.project.model.repository;

import com.assessing.project.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer> {
    ArrayList<Mark> findMarksByStudentAndSemester(Student student, Integer semester);

    @Query("select sum(m.value) from Mark m where m.student = :#{#student} and m.semester = :#{#semester}")
    Integer findSumOfMark(@Param("student") Student student, @Param("semester") Integer semester);

    @Query("select count(m) from Mark m where m.student.id = :#{#student.id} and m.semester = :#{#semester} and m.value is not null")
    Integer findCountOfMark(@Param("student") Student student, @Param("semester") Integer semester);

    Mark findMarkByStudentAndSubject(Student student, Subject subject);
    ArrayList<Mark> findMarksBySubject(Subject subject);

    @Query("select m from Mark as m inner join m.subject as s on :#{#teacher} = s.teacher inner join m.student as st on " +
            ":#{#group} = st.group")
    ArrayList<Mark> findMarksByGroupAndTeacher(@Param("group") Group group,
                                               @Param("teacher") Teacher teacher);

    @Query("select m from Mark as m inner join m.subject as s on :#{#teacher} = s.teacher inner join m.student as st on " +
            ":#{#student} = st")
    Mark findMarkByStudentAndTeacher(@Param("student") Student student,
                                               @Param("teacher") Teacher teacher);

//    @Query("select m from Mark as m")
//    ArrayList<Mark> findMarkByGroupAndHeightMark(@Param("group") Group group);
}
