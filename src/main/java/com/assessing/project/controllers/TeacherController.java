package com.assessing.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {

    @GetMapping("/teacher")
    public String teacher(Model model){
        model.addAttribute("title", "Інф. викладача");
        return "teacher";
    }
    @GetMapping("/teacher_report")
    public String teacher_report(Model model){
        model.addAttribute("title", "Інф. викладача");
        return "Teacher_report";
    }
}
