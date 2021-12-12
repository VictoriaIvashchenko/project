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
    public String addAdmin(Model model){
        model.addAttribute("title", "Додати адміністратора");
        return "add_admin";
    }
    @GetMapping("/add_student")
    public String addStudent(Model model){
        model.addAttribute("title", "Додати студента");
        return "add_student";
    }
    @GetMapping("/add_teacher")
    public String addTeacher(Model model){
        model.addAttribute("title", "Додати викладача");
        return "add_teacher";
    }

    @GetMapping("/exams")
    public String exams(Model model){
        model.addAttribute("title", "Результати сесії");
        return "exams";
    }
    @GetMapping("/exams_faculty")
    public String examsFaculty(Model model){
        model.addAttribute("title", "Результати сесії по факультету");
        return "exams_faculty";
    }
    @GetMapping("/exams_speciality")
    public String examsSpeciality(Model model){
        model.addAttribute("title", "Результати сесії по спецільності");
        return "exams_speciality";
    }
    @GetMapping("/exams_course")
    public String examsCourse(Model model){
        model.addAttribute("title", "Результати сесії по курсу");
        return "exams_course";
    }
    @GetMapping("/exams_group")
    public String examsGroup(Model model){
        model.addAttribute("title", "Результати сесії по групі");
        return "exams_group";
    }
}
