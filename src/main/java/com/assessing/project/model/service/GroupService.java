package com.assessing.project.model.service;

import com.assessing.project.model.entity.Group;
import com.assessing.project.model.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    private GroupRepository groupRepository;
@Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    public String findGroupName(Group group){return group.getName();}
}
