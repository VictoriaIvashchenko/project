package com.assessing.project.controllers;

import com.assessing.project.model.entity.Student;
import com.assessing.project.model.repository.GroupRepository;
import com.assessing.project.model.repository.SubjectRepository;
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
    @Autowired
    public MainController(StudentService studentServicee, GroupRepository groupRepository, SubjectRepository subjectRepository) {
        this.studentServicee = studentServicee;
        this.groupRepository = groupRepository;
        this.subjectRepository = subjectRepository;
    }

    private StudentService studentServicee;
    private GroupRepository groupRepository;
    private SubjectRepository subjectRepository;
    @GetMapping("/")
    public String enter(Model model) {
        ArrayList<Student> students = studentServicee.findStudentsByGroupAndHeightMark(groupRepository.getById(1));
        String str = students.get(0).getSurname();
//        String str1 = students.get(1).toString();
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
