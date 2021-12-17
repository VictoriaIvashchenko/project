package com.assessing.project.controllers;

import com.assessing.project.model.entity.Faculty;
import com.assessing.project.model.entity.Group;
import com.assessing.project.model.repository.FacultyRepository;
import com.assessing.project.model.service.FacultyService;
import com.assessing.project.model.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    FacultyService facultyService;
    @Autowired
    GroupService groupService;


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
    @GetMapping("/admin_report")
    public String adminReport(Model model){
        model.addAttribute("title", "Звіти");
        return "admin_report";
    }
    @GetMapping("/admin_report_faculty")
    public String adminReportFaculty(Model model){
        model.addAttribute("title", "Звіти по факультету");
        ArrayList<String> faculties = facultyService.getAll();
        model.addAttribute("faculties", faculties);
        return "admin_report_faculty";
    }
    @GetMapping("/admin_report_speciality")
    public String adminReportSpeciality(Model model){
        model.addAttribute("title", "Звіти по спецільності");
//        List<Faculty> faculties = facultyRepository.findAll();
//        model.addAttribute("faculties", faculties);
        return "admin_report_speciality";
    }
    @GetMapping("/admin_report_course")
    public String adminReportCourse(Model model){
        model.addAttribute("title", "Звіти по курсу");
        return "admin_report_course";
    }
    @GetMapping("/admin_report_group")
    public String adminReportGroup(Model model){
        model.addAttribute("title", "Звіти по групі");
        Iterable<Group> groups = groupService.getAll();
        model.addAttribute("groups", groups);
        return "admin_report_group";
    }
    @GetMapping("/adddata")
    public String adddata(Model model){
        model.addAttribute("title", "Додати дані");
        return "adddata";
    }
    @GetMapping("/adddata_faculty")
    public String adddataFaculty(Model model){
        model.addAttribute("title", "Додати новий факультет");
        return "adddata_faculty";
    }
    @GetMapping("/adddata_speciality")
    public String adddataSpeciality(Model model){
        model.addAttribute("title", "Додати нову спеціальність");
        return "adddata_speciality";
    }

    @GetMapping("/adddata_group")
    public String adddataGroup(Model model){
        model.addAttribute("title", "Додати нову групу");
        return "adddata_group";
    }
    @PostMapping("/adddata_faculty")
    public String addFaculty(@RequestParam String name, Model model){

//        facultyService.create(name);
        return "adddata";
    }

}
