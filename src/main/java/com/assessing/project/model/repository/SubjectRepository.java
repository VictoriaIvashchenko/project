package com.assessing.project.model.repository;

import com.assessing.project.model.entity.Group;
import com.assessing.project.model.entity.Mark;
import com.assessing.project.model.entity.Subject;
import com.assessing.project.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query("select distinct s.name from Subject as s inner join s.marks on :#{#mark.subject.id} = s.id")
    String customFindSubjectByMark(@Param("mark") Mark mark);

    @Query("select distinct s.test_type from Subject as s inner join s.marks on :#{#mark.subject.id} = s.id")
    String customFindTestTypeByMark(@Param("mark") Mark mark);

    Subject findSubjectByGroupsAndTeacher(Group group, Teacher teacher);

    ArrayList<Subject> findSubjectsByTeacher(Teacher teacher);

    @Query("select s.test_type from Subject as s where :#{#subject} = s")
    String findTestTypeBySubject(@Param("subject") Subject subject);

    Subject findSubjectByName(String name);
    Subject findSubjectById(Integer id);
}
