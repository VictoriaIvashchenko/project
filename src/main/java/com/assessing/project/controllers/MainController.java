package com.assessing.project.controllers;

import com.assessing.project.model.entity.Group;
import com.assessing.project.model.repository.SubjectRepository;
import com.assessing.project.model.repository.TeacherRepository;
import com.assessing.project.model.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class MainController {
    @Autowired
    public MainController(GroupService groupService, TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        this.groupService = groupService;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    private GroupService groupService;
    private TeacherRepository teacherRepository;
    private SubjectRepository subjectRepository;
    @GetMapping("/")
    public String enter(Model model) {
        ArrayList<String> groups = groupService.findFacultyByTeacherGroup(teacherRepository.getById(1), subjectRepository.getById(4));
        String str = groups.get(0);
        String str1 = groups.get(1);
//        Teacher value = teacherService.findByMark(markRepository.getById(1));
//        String name = value.getName();
//        String patronymyc = value.getPatronymic();
//        String surname = value.getSurname();
        model.addAttribute("title", "Вхід");
        return "enter";
    }

    @PostMapping("/")
    public String authorisation(@RequestParam String login, @RequestParam String password, Model model){
        //пробник чи працює, потім замінить на перевірку в бд
        if(login.equals("katya") && password.equals("123")) {
            return "/student";
        }
        else{
            return "/";
        }
    }



}
