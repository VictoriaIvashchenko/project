package com.assessing.project.controllers;

import com.assessing.project.model.entity.Group;
import com.assessing.project.model.repository.StudentRepository;
import com.assessing.project.model.repository.SubjectRepository;
import com.assessing.project.model.service.GroupService;
import com.assessing.project.model.service.SpecialityService;
import com.assessing.project.model.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class MainController {
//    @Autowired
//    public MainController(StudentService studentService, SpecialityService specialityService, StudentRepository studentRepository, GroupService groupService, SubjectRepository subjectRepository) {
//        this.studentService = studentService;
//        this.specialityService = specialityService;
//        this.studentRepository = studentRepository;
//        this.groupService = groupService;
//        this.subjectRepository = subjectRepository;
//    }
//
//    private StudentService studentService;
//    private SpecialityService specialityService;
//    private StudentRepository studentRepository;
//    private GroupService groupService;
//    private SubjectRepository subjectRepository;
    @GetMapping("/")
    public String enter(Model model) {
//        ArrayList<Group> groups = groupService.findGroupBySubject(subjectRepository.getById(1));
//        System.out.println(groups.get(0).getName() + "   " + groups.get(1).getName());
//        String str = specialityService.findSpecialityByStudents(studentRepository.getById(1));
//        Teacher value = teacherService.findByMark(markRepository.getById(1));
//        String name = value.getName();
//        String patronymyc = value.getPatronymic();
//        String surname = value.getSurname();
        model.addAttribute("title", "Вхід");
        return "enter";
    }
    @GetMapping("/error_login")
    public String error(Model model) {
        return "error_login";
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
