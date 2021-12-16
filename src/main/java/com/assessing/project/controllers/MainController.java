package com.assessing.project.controllers;

import com.assessing.project.model.entity.Subject;
import com.assessing.project.model.entity.Teacher;
import com.assessing.project.model.repository.MarkRepository;
import com.assessing.project.model.service.SubjectService;
import com.assessing.project.model.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
//    @Autowired
//    public MainController(TeacherService teacherService, MarkRepository markRepository) {
//        this.teacherService = teacherService;
//        this.markRepository = markRepository;
//    }
//
//    private TeacherService teacherService;
//    private MarkRepository markRepository;
    @GetMapping("/")
    public String enter(Model model) {
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
