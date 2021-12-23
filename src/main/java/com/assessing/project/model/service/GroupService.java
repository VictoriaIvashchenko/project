package com.assessing.project.model.service;

import com.assessing.project.model.entity.*;
import com.assessing.project.model.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    public String findGroupName(Group group){return group.getName();}
    public ArrayList<String> findGroupByTeacher(Teacher teacher, Subject subject){
        return groupRepository.findGroupByTeacher(teacher, subject);
    }
    public ArrayList<String> findFacultyByTeacherGroup(Teacher teacher, Subject subject){
        return groupRepository.findFacultyByTeacherGroup(teacher, subject);
    }
    public ArrayList<String> findGroupName(){
        return groupRepository.findGroupName();
    }
    public Group findGroupByName(String name){
        return groupRepository.findGroupByName(name);
    }
    public ArrayList<Group> findGroupsByFaculty(Faculty faculty){
        return groupRepository.findGroupsByFaculty(faculty);
    }
    public ArrayList<Group> findGroupsByTeacher(Teacher teacher){return groupRepository.findGroupsByTeacher(teacher);}
    public ArrayList<Group> findGroupBySubject(Subject subject){return groupRepository.findGroupBySubject(subject);}
    public List<Group> findAllGroups(){
        return groupRepository.findAll();
    }
    public String findGroupByStudent(Student student){return  groupRepository.findGroupByStudent(student);}
    public void create(String name, Faculty faculty, Speciality speciality){
        Group group = new Group(name, faculty, speciality);
        groupRepository.save(group);
    }
    public void update(Group group){
        groupRepository.save(group);
    }
    public void delete(Group group){
        groupRepository.delete(group);
    }

}
