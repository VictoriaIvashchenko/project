package com.assessing.project.model.repository;

import com.assessing.project.model.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {
    @Query("select s.name from Speciality as s")
    ArrayList<String> findSpecialityName();
    Speciality findSpecialityByName(String name);
}
