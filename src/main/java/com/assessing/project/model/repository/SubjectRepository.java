package com.assessing.project.model.repository;

import com.assessing.project.model.entity.Mark;
import com.assessing.project.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query("select distinct s.name from Subject as s inner join s.marks on :#{#mark.subject.id} = s.id")
    String customFindSubjectByMark(@Param("mark") Mark mark);

    @Query("select distinct s.test_type from Subject as s inner join s.marks on :#{#mark.subject.id} = s.id")
    String customFindTestTypeByMark(@Param("mark") Mark mark);
}
