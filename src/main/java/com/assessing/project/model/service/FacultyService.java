package com.assessing.project.model.service;

import com.assessing.project.model.entity.Faculty;
import com.assessing.project.model.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
