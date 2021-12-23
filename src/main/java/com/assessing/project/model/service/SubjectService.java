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
    private SubjectRepository subjectRepository;
    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    public String findSubjectNameByMark(Mark mark){return subjectRepository.customFindSubjectByMark(mark);}
    public String findTestTypeByMark(Mark mark){return subjectRepository.customFindTestTypeByMark(mark);}
    public Subject findSubjectNameByGroupAndTeacher(Group group, Teacher teacher){
        return subjectRepository.findSubjectByGroupsAndTeacher(group, teacher);
    }
    public Subject findSubjectById(Integer id){
        return subjectRepository.findSubjectById(id);
    }
    public Integer findIdBySubject(Subject subject){
        return subject.getId();
    }
    public ArrayList<Subject> findSubjectsByTeacher(Teacher teacher){return subjectRepository.findSubjectsByTeacher(teacher);}
    public String findSubjectName(Subject subject){return subject.getName();}
    public String findTestTypeBySubject(Subject subject){return subjectRepository.findTestTypeBySubject(subject);}
    public Subject findSubjectByName(String name){return subjectRepository.findSubjectByName(name);}
    public void create(String name, String testType, Teacher teacher){
        Subject subject = new Subject(name, testType, teacher);
        subjectRepository.save(subject);
    }
    public void update(Subject subject){
        subjectRepository.save(subject);
    }
    public void delete(Subject subject){subjectRepository.delete(subject);}
}