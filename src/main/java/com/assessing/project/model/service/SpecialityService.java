package com.assessing.project.model.service;

import com.assessing.project.model.entity.Speciality;
import com.assessing.project.model.entity.Student;
import com.assessing.project.model.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialityService {
    private SpecialityRepository specialityRepository;
@Autowired
    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }
    public ArrayList<String> findSpecialityName(){return findSpecialityName();}
    public Speciality findSpecialityByName(String name){
        return specialityRepository.findSpecialityByName(name);
    }
    public List<Speciality> findAllSpecialities(){
        return specialityRepository.findAll();
    }
    public String findSpecialityByStudents(Student student){
        return specialityRepository.findSpecialityByStudent(student);
    }
}
