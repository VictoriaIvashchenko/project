package com.assessing.project.model.service;

import com.assessing.project.model.entity.Group;
import com.assessing.project.model.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectGroupService {

    private GroupService groupService;
    @Autowired
    public SubjectGroupService(GroupService groupService, SubjectService subjectService) {
        this.groupService = groupService;
        this.subjectService = subjectService;
    }


    SubjectService subjectService;
    public void create(Subject subject, Group group){
        Subject subject1 = subject;
        Group group1 = group;
        subject1.addGroups(group1);
        group1.addSubjects(subject1);
        groupService.update(group1);
        subjectService.update(subject1);
    }
}
