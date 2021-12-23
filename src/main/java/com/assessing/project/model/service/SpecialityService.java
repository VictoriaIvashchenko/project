package com.assessing.project.model.service;

import com.assessing.project.model.entity.Group;
import com.assessing.project.model.entity.Speciality;
import com.assessing.project.model.entity.Student;
import com.assessing.project.model.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialityService {
    private SpecialityRepository specialityRepository;
    @Autowired
    public SpecialityService(SpecialityRepository specialityRepository){
        this.specialityRepository = specialityRepository;
    }
    public ArrayList<String> findSpecialityName(){return specialityRepository.findSpecialityName();}
    public Speciality findSpecialityByName(String name){
        return specialityRepository.findSpecialityByName(name);
    }
    public List<Speciality> findAllSpecialities(){
        return specialityRepository.findAll();
    }
    public String findSpecialityByStudents(Student student){
        return specialityRepository.findSpecialityByStudent(student);
    }
    public String findSpecialityByGroup(Group group){
        return specialityRepository.findSpecialityByGroup(group);
    }
    public String findSpecialityName(Speciality speciality){
        return speciality.getName();
    }

    public void create(String name){
        Speciality speciality = new Speciality(name);
        specialityRepository.save(speciality);
    }
    public void update(Speciality speciality){
        specialityRepository.save(speciality);
    }
    public void delete(Speciality speciality){
        specialityRepository.delete(speciality);
    }
}
