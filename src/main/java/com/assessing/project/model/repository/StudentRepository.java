package com.assessing.project.model.repository;

import com.assessing.project.model.entity.Group;
import com.assessing.project.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    ArrayList<Student> findStudentsByGroup(Group group);
//    ArrayList<Student> findStudentsBy
    @Query("select s from Student as s inner join s.group as g on g = :#{#group} where 90 <= " +
        "all(select m.value from Mark as m where m.student = s)")
//            "inner join m.student on m.student.group = :#{#group} and )")
    ArrayList<Student> findStudentsByGroupAndHeightMark(@Param("group") Group group);
}
