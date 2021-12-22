package com.assessing.project.model.repository;

import com.assessing.project.model.entity.Mark;
import com.assessing.project.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findTeacherByLogin(String login);

    @Query("select t.password from Teacher as t where t.login = :#{#login}")
    String findTeacherPasswordByLogin(@Param("login") String login);
    @Query("select distinct t from Teacher as t inner join t.subjects as s on :#{#mark.subject.teacher.id} = t.id inner join " +
            "s.marks on :#{#mark.subject.id}=s.id")
    Teacher customFindTeacherByMark(@Param("mark") Mark mark);
    Teacher findTeacherBySurnameAndName(String surname, String name);
    Teacher findTeacherBySurname(String surname);
    Teacher findTeacherById(Integer id);
}
