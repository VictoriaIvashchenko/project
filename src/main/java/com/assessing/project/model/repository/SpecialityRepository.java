package com.assessing.project.model.repository;

import com.assessing.project.model.entity.Group;
import com.assessing.project.model.entity.Speciality;
import com.assessing.project.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {
    @Query("select s.name from Speciality as s")
    ArrayList<String> findSpecialityName();

    Speciality findSpecialityByName(String name);

    @Query("select distinct s.name from Speciality as s where :#{#student.speciality} = s")
    String findSpecialityByStudent(@Param("student") Student student);

    @Query("select distinct s.name from Speciality as s where :#{#group.speciality} = s")
    String findSpecialityByGroup(@Param("group") Group group);
}
