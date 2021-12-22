package com.assessing.project.model.repository;

import com.assessing.project.model.entity.Faculty;
import com.assessing.project.model.entity.Group;
import com.assessing.project.model.entity.Speciality;
import com.assessing.project.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findStudentByLogin(String login);
    @Query("select s.password from Student as s where s.login = :#{#login}")
    String findStudentPasswordByLogin(@Param("login") String login);
    ArrayList<Student> findStudentsByGroup(Group group);
    Student findStudentBySurnameAndName(String surname, String name);
    ArrayList<Student> findStudentsByCourse(Integer course);
    ArrayList<Student> findStudentsBySpeciality(Speciality speciality);
    ArrayList<Student> findStudentsByFaculty(Faculty faculty);

    @Query("select s from Student as s inner join s.group as g on g = :#{#group} where 95 <= " +
        "all(select m.value from Mark as m where m.student = s and m.semester = :#{#semester})")
    ArrayList<Student> findStudentsByGroupAndHeightMark(@Param("group") Group group,
                                                        @Param("semester") Integer semester);

    @Query("select s from Student as s inner join s.group as g on g = :#{#group} where 60 > " +
            "any(select m.value from Mark as m where m.student = s and m.semester = :#{#semester})")
    ArrayList<Student> findStudentsByGroupAndLowestMark(@Param("group") Group group,
                                                        @Param("semester") Integer semester);

    @Query("select s from Student as s inner join s.faculty as f on f = :#{#faculty} where 95 <= " +
            "all(select m.value from Mark as m where m.student = s and m.semester = :#{#semester})")
    ArrayList<Student> findStudentsByFacultyAndHeightMark(@Param("faculty") Faculty faculty,
                                                          @Param("semester") Integer semester);

    @Query("select s from Student as s inner join s.faculty as f on f = :#{#faculty} where 60 > " +
            "any(select m.value from Mark as m where m.student = s and m.semester = :#{#semester})")
    ArrayList<Student> findStudentsByFacultyAndLowestMark(@Param("faculty") Faculty faculty,
                                                          @Param("semester") Integer semester);

    @Query("select s from Student as s inner join s.speciality as sp on sp = :#{#speciality} where 95 <= " +
            "all(select m.value from Mark as m where m.student = s and m.semester = :#{#semester})")
    ArrayList<Student> findStudentsBySpecialityAndHeightMark(@Param("speciality") Speciality speciality, @Param("semester") Integer semester);

    @Query("select s from Student as s inner join s.speciality as sp on sp = :#{#speciality} where 60 > " +
            "any(select m.value from Mark as m where m.student = s and m.semester = :#{#semester})")
    ArrayList<Student> findStudentsBySpecialityAndLowestMark(@Param("speciality") Speciality speciality, @Param("semester") Integer semester);

    @Query("select distinct s from Student as s inner join s.marks as m on m.semester = :#{#semester} where 95 <= " +
            "all(select m.value from Mark as m where m.student = s)")
    ArrayList<Student> findStudentsBySemesterAndHeightMark(@Param("semester") Integer semester);

    @Query("select distinct s from Student as s inner join s.marks as m on m.semester = :#{#semester} where 60 > " +
            "any(select m.value from Mark as m where m.student = s)")
    ArrayList<Student> findStudentsBySemesterAndLowestMark(@Param("semester") Integer semester);

}
