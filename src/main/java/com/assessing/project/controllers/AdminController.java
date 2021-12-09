package com.assessing.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("title", "Сторінка адміністратора");
        return "admin";
    }

    @GetMapping("/Admin_teacher_page")
    public String adminTeacherPage(Model model){
        model.addAttribute("title", "Сторінка інформаці про викладача");
        return "/Admin_teacher_page";
    }

    @GetMapping("/Admin_teacher_page_add_new_subject")
    public String adminAddSubject(Model model){
        model.addAttribute("title", "Додати новий предмет");
        return "/Admin_teacher_page_add_new_subject";
    }

    @GetMapping("/Admin_teacher_page_subject_info")
    public String adminInfoSubject(Model model){
        model.addAttribute("title", "Іформація про предмет");
        return "/Admin_teacher_page_subject_info";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("title", "Додати користувача");
        return "add";
    }
    @GetMapping("/add_admin")
    public String add_admin(Model model){
        model.addAttribute("title", "Додати адміністратора");
        return "add_admin";
    }
    @GetMapping("/add_student")
    public String add_student(Model model){
        model.addAttribute("title", "Додати студента");
        return "add_student";
    }
    @GetMapping("/add_teacher")
    public String add_teacher(Model model){
        model.addAttribute("title", "Додати викладача");
        return "add_teacher";
    }

}
