package com.assessing.project.model.service;

import com.assessing.project.model.entity.Group;
import com.assessing.project.model.entity.Mark;
import com.assessing.project.model.entity.Subject;
import com.assessing.project.model.entity.Teacher;
import com.assessing.project.model.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    public String findSubjectNameByMark(Mark mark){return subjectRepository.customFindSubjectByMark(mark);}
    public String findTestTypeByMark(Mark mark){return subjectRepository.customFindTestTypeByMark(mark);}
    public String findSubjectNameByGroupAndTeacher(Group group, Teacher teacher){
        return subjectRepository.findSubjectByGroupsAndTeacher(group, teacher).getName();
    }
    public ArrayList<Subject> findSubjectsByTeacher(Teacher teacher){return subjectRepository.findSubjectsByTeacher(teacher);}
    public String findSubjectName(Subject subject){return subject.getName();}
    public String findTestTypeBySubject(Subject subject){return subjectRepository.findTestTypeBySubject(subject);}
    public Subject findSubjectByName(String name){return subjectRepository.findSubjectByName(name);}
}