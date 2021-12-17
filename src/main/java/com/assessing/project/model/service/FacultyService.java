package com.assessing.project.model.service;

import com.assessing.project.model.entity.Faculty;
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
    public ArrayList<String> getAll() {
        Iterable<Faculty> faculties = facultyRepository.findAll();
        ArrayList<String> names = new ArrayList<>();

        for (Faculty faculty: faculties) {
            names.add(faculty.getName());

        }
        return names;

    }
}
