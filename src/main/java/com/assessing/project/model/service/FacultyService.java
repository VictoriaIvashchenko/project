package com.assessing.project.model.service;

import com.assessing.project.model.entity.Faculty;
import com.assessing.project.model.entity.Group;
import com.assessing.project.model.entity.Student;
import com.assessing.project.model.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FacultyService {
    private FacultyRepository facultyRepository;
    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public String findFacultyName(Faculty faculty){
        return faculty.getName();
    }
    public void create(String name){
        Faculty faculty = new Faculty(name);
        facultyRepository.save(faculty);
    }
    public void update(Faculty faculty){
        facultyRepository.save(faculty);
    }
    public void delete(Faculty faculty){
        facultyRepository.delete(faculty);
    }
    public ArrayList<String> findFacultyName() {
        return facultyRepository.findFacultyName();

    }
    public Faculty findFacultyByName(String name){
        return facultyRepository.findFacultyByName(name);
    }
    public Faculty findFacultyByGroup(Group group){return facultyRepository.findFacultyByGroup(group);}
    public Faculty findFacultyByStudent(Student student){return  facultyRepository.findFacultyByStudent(student);}
}
