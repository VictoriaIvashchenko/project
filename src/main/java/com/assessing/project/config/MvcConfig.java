package com.assessing.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
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
        registry.addViewController("/Admin_teacher_page").setViewName("add");
        registry.addViewController("/Admin_teacher_page_add_new_subject").setViewName("add");
        registry.addViewController("/Admin_teacher_page_subject_info").setViewName("add");
        registry.addViewController("/exams").setViewName("add");
        registry.addViewController("/exams_course").setViewName("add");
        registry.addViewController("/exams_faculty").setViewName("add");
        registry.addViewController("/exams_group").setViewName("add");
        registry.addViewController("/exams_speciality").setViewName("add");
        registry.addViewController("/teacher").setViewName("teacher");
        registry.addViewController("/Teacher_report").setViewName("teacher");
        registry.addViewController("/Teacher_report_group_option").setViewName("teacher");
        registry.addViewController("/Teacher_report_subject_option").setViewName("teacher");

    }

}