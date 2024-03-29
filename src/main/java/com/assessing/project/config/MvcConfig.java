package com.assessing.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/error_login").setViewName("error_login");
        registry.addViewController("/student").setViewName("student");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/add").setViewName("add");
        registry.addViewController("/add_admin").setViewName("add");
        registry.addViewController("/add_student").setViewName("add");
        registry.addViewController("/add_teacher").setViewName("add");
        registry.addViewController("/adddata").setViewName("add");
        registry.addViewController("/adddata_faculty").setViewName("add");
        registry.addViewController("/adddata_group").setViewName("add");
        registry.addViewController("/adddata_speciality").setViewName("add");
        registry.addViewController("/admin_report").setViewName("add");
        registry.addViewController("/admin_report_course").setViewName("add");
        registry.addViewController("/admin_report_faculty").setViewName("add");
        registry.addViewController("/admin_report_group").setViewName("add");
        registry.addViewController("/admin_report_speciality").setViewName("add");
        registry.addViewController("/admin_teacher_page").setViewName("add");
        registry.addViewController("/admin_teacher_page_add_new_subject").setViewName("add");
        registry.addViewController("/admin_teacher_page_subject_info").setViewName("add");
        registry.addViewController("/exams").setViewName("add");
        registry.addViewController("/exams_course").setViewName("add");
        registry.addViewController("/exams_faculty").setViewName("add");
        registry.addViewController("/exams_group").setViewName("add");
        registry.addViewController("/exams_speciality").setViewName("add");
        registry.addViewController("/teacher").setViewName("teacher");
        registry.addViewController("/teacher_report").setViewName("teacher");
        registry.addViewController("/teacher_report_group").setViewName("teacher");
        registry.addViewController("/teacher_report_subject").setViewName("teacher");
        registry.addViewController("/teacher_set_marks").setViewName("teacher");
        registry.addViewController("/admin_student_page").setViewName("add");
        registry.addViewController("/admin_teacher_subject_add_group").setViewName("add");
        registry.addViewController("/edit_data").setViewName("add");
        registry.addViewController("/edit_data_faculty").setViewName("add");
        registry.addViewController("/edit_data_group").setViewName("add");
        registry.addViewController("/edit_data_speciality").setViewName("add");
        registry.addViewController("/edit_student").setViewName("add");
        registry.addViewController("/edit_teacher").setViewName("add");
        registry.addViewController("/edit_subject").setViewName("add");
        registry.addViewController("/teacher_set_marks").setViewName("teacher");
    }

}