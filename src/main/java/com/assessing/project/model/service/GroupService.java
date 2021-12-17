package com.assessing.project.model.service;

import com.assessing.project.model.entity.Group;
import com.assessing.project.model.entity.Subject;
import com.assessing.project.model.entity.Teacher;
import com.assessing.project.model.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
}
