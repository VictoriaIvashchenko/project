package com.assessing.project.controllers;

import com.assessing.project.additional.InfoForReport;
import com.assessing.project.config.SecurityUser;
import com.assessing.project.model.entity.*;
import com.assessing.project.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class AdminController {
    @Autowired
    FacultyService facultyService;
    @Autowired
    GroupService groupService;
    @Autowired
    AdminService adminService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    SpecialityService specialityService;
    @Autowired
    StudentService studentService;
    @Autowired
    MarkService markService;
    @Autowired
    SubjectService subjectService;

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("title", "Сторінка адміністратора");
        //видобуток поточного адміністратора після авторизації
        SecurityUser curr = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Admin currentAdmin = adminService.findByLogin(curr.getUsername());
        return "admin";
    }

    @GetMapping("/admin_teacher_page")
    public String adminTeacherPage(Model model){
        model.addAttribute("title", "Сторінка інформаці про викладача");
        return "admin_teacher_page";
    }

    @GetMapping("/admin_teacher_page_add_new_subject")
    public String adminAddSubject(Model model){
        model.addAttribute("title", "Додати новий предмет");
        return "admin_teacher_page_add_new_subject";
    }

    @GetMapping("/admin_teacher_page_subject_info")
    public String adminInfoSubject(Model model){
        model.addAttribute("title", "Іформація про предмет");
        return "admin_teacher_page_subject_info";
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
        ArrayList<String> faculties = facultyService.findFacultyName();
        model.addAttribute("faculties", faculties);
        ArrayList<String> groups = groupService.findGroupName();
        model.addAttribute("groups", groups);
        ArrayList<String> specialities = specialityService.findSpecialityName();
        model.addAttribute("specialities", specialities);
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
        ArrayList<String> faculties = facultyService.findFacultyName();
        model.addAttribute("faculties", faculties);
        return "exams_faculty";
    }
    @GetMapping("/exams_speciality")
    public String examsSpeciality(Model model){
        model.addAttribute("title", "Результати сесії по спецільності");
        ArrayList<String> specialities = specialityService.findSpecialityName();
        model.addAttribute("specialities", specialities);
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
        ArrayList<String> groups = groupService.findGroupName();
        model.addAttribute("groups", groups);
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
        ArrayList<String> faculties = facultyService.findFacultyName();
        model.addAttribute("faculties", faculties);
        return "admin_report_faculty";
    }
    @GetMapping("/admin_report_speciality")
    public String adminReportSpeciality(Model model){
        model.addAttribute("title", "Звіти по спецільності");
        ArrayList<String> specialities = specialityService.findSpecialityName();
        model.addAttribute("specialities", specialities);
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
        ArrayList<String> groups = groupService.findGroupName();
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

        ArrayList<String> faculties = facultyService.findFacultyName();
        model.addAttribute("faculties", faculties);
        ArrayList<String> specialities = specialityService.findSpecialityName();
        model.addAttribute("specialities", specialities);

        return "adddata_group";
    }
    @PostMapping("/adddata_faculty")
    public String addFaculty(@RequestParam String name, Model model){
        facultyService.create(name);
        return "redirect:/adddata";
    }
    @PostMapping("/adddata_speciality")
    public String addSpeciality(@RequestParam String name, Model model){
        specialityService.create(name);
        return "redirect:/adddata";
    }
    @PostMapping("/adddata_group")
    public String addGroup(@RequestParam String name, @RequestParam String facultyName,
                           @RequestParam String specialityName, Model model){

        groupService.create(name, facultyService.findFacultyByName(facultyName),
                specialityService.findSpecialityByName(specialityName));

        return "redirect:/adddata";
    }

    @PostMapping("/add_admin")
    public String addAdminPOST(@RequestParam("name") String name, @RequestParam("surname") String surname,
                               @RequestParam("patronymic") String patronymic, @RequestParam("login") String login,
                               @RequestParam("password") String password, Model model){

        adminService.create(name, surname, patronymic, login, password);
        return"redirect:/add";
    }
    @PostMapping("/add_teacher")
    public String addTeacherPOST(@RequestParam("name") String name, @RequestParam("surname") String surname,
                                 @RequestParam("patronymic") String patronymic, @RequestParam("login") String login,
                                 @RequestParam("password") String password, Model model){

        teacherService.create(surname, name, patronymic, login, password);
        return"redirect:/add";
    }
    @PostMapping("/add_student")
    public String addStudentPOST(@RequestParam("name") String name, @RequestParam("surname") String surname,
                                 @RequestParam("patronymic") String patronymic,@RequestParam("groupName") String groupName,
                                 @RequestParam("facultyName") String facultyName, @RequestParam("specialityName") String specialityName,
                                 @RequestParam("course") Integer course, @RequestParam("login") String login,
                                 @RequestParam("password") String password, Model model){


        studentService.create(surname, name, patronymic, facultyService.findFacultyByName(facultyName),
                specialityService.findSpecialityByName(specialityName),groupService.findGroupByName(groupName),course, login, password);
        return"redirect:/add";
    }
    @PostMapping("/admin_report_faculty")
    public String adminReportFacultyPost(@RequestParam("facultyName") String facultyName,@RequestParam("semester") Integer semester, Model model){
        ArrayList<String> faculties = facultyService.findFacultyName();
        model.addAttribute("faculties", faculties);
        Faculty faculty = facultyService.findFacultyByName(facultyName);
        if (facultyName.equals("--")) {
            model.addAttribute("tables", "nothing");
        }
        else {
            model.addAttribute("tables", "something");
            ArrayList<Student> studentsHeight = studentService.findStudentsByFacultyAndHeightMark(faculty, semester);


            ArrayList<Student> studentsLow = studentService.findStudentsByFacultyAndLowestMark(faculty, semester);
            if (studentsHeight.size()==0 || semester == 0){
                model.addAttribute("tableHeight", "nothing");
            }
            else {

                model.addAttribute("tableHeight", "something");
                ArrayList<InfoForReport> infoStudentsHeight = new ArrayList<>();
                int i = 1;
                for (Student student: studentsHeight) {
                    InfoForReport infoStudentHeight = new InfoForReport(i,  studentService.findCourse(student),
                            specialityService.findSpecialityByStudents(student),studentService.findStudentName(student),
                            groupService.findGroupByStudent(student), markService.findAverageMark(student, semester));

                    infoStudentsHeight.add(infoStudentHeight);
                    i++;
                }
                model.addAttribute("studentsHeight", infoStudentsHeight);

            }
            if (studentsLow.size()==0){
                model.addAttribute("tableLow", "nothing");
            }
            else {
                model.addAttribute("tableLow", "something");
                ArrayList<InfoForReport> infoStudentsLow = new ArrayList<>();

                int i = 1;
                for (Student student: studentsLow) {
                    InfoForReport infoStudentLow = new InfoForReport(i,  studentService.findCourse(student),
                            specialityService.findSpecialityByStudents(student),studentService.findStudentName(student),
                            groupService.findGroupByStudent(student), markService.findAverageMark(student, semester));
                    infoStudentsLow.add(infoStudentLow);
                    i++;
                }
                model.addAttribute("studentsLow", infoStudentsLow);
            }
        }

        return "admin_report_faculty";
    }
    @PostMapping("/admin_report_speciality")
    public String adminReportSpecialityPost(@RequestParam("specialityName") String specialityName, @RequestParam("semester") Integer semester, Model model){
        ArrayList<String> specialities = specialityService.findSpecialityName();
        model.addAttribute("specialities", specialities);
        Speciality speciality = specialityService.findSpecialityByName(specialityName);
        if (specialityName.equals("--") || semester == 0) {
            model.addAttribute("tables", "nothing");
        }
        else {
            model.addAttribute("tables", "something");
            ArrayList<Student> studentsHeight = studentService.findStudentsBySpecialityAndHeightMark(speciality, semester);


            ArrayList<Student> studentsLow = studentService.findStudentsBySpecialityAndLowestMark(speciality,semester);
            if (studentsHeight.size()==0){
                model.addAttribute("tableHeight", "nothing");
            }
            else {

                model.addAttribute("tableHeight", "something");
                ArrayList<InfoForReport> infoStudentsHeight = new ArrayList<>();
                int i = 1;
                for (Student student: studentsHeight) {
                    InfoForReport infoStudentHeight = new InfoForReport(i, facultyService.findFacultyName(facultyService.findFacultyByStudent(student)), studentService.findCourse(student),
                            studentService.findStudentName(student),
                            groupService.findGroupByStudent(student), markService.findAverageMark(student, semester));

                    infoStudentsHeight.add(infoStudentHeight);
                    i++;
                }
                model.addAttribute("studentsHeight", infoStudentsHeight);

            }
            if (studentsLow.size()==0){
                model.addAttribute("tableLow", "nothing");
            }
            else {
                model.addAttribute("tableLow", "something");
                ArrayList<InfoForReport> infoStudentsLow = new ArrayList<>();

                int i = 1;
                for (Student student: studentsLow) {
                    InfoForReport infoStudentLow = new InfoForReport(i, facultyService.findFacultyName(facultyService.findFacultyByStudent(student)), studentService.findCourse(student),
                            studentService.findStudentName(student),
                            groupService.findGroupByStudent(student), markService.findAverageMark(student, semester));
                    infoStudentsLow.add(infoStudentLow);
                    i++;
                }
                model.addAttribute("studentsLow", infoStudentsLow);
            }
        }
        return "admin_report_speciality";
    }
    @PostMapping("/admin_report_group")
    public String adminReportGroupPost(@RequestParam("groupName") String groupName, @RequestParam("semester") Integer semester, Model model){
        ArrayList<String> groups = groupService.findGroupName();
        model.addAttribute("groups", groups);
        Group group = groupService.findGroupByName(groupName);
        if (groupName.equals("--") || semester == 0) {
            model.addAttribute("tables", "nothing");
        }
        else {
            model.addAttribute("tables", "something");
            ArrayList<Student> studentsHeight = studentService.findStudentsByGroupAndHeightMark(group, semester);
            ArrayList<Student> studentsLow = studentService.findStudentsByGroupAndLowestMark(group, semester);

            if (studentsHeight.size()==0){
                model.addAttribute("tableHeight", "nothing");
            }
            else {

                model.addAttribute("tableHeight", "something");
                ArrayList<InfoForReport> infoStudentsHeight = new ArrayList<>();
                int i = 1;
                for (Student student: studentsHeight) {
                    InfoForReport infoStudentHeight = new InfoForReport(i,
                            studentService.findStudentName(student), markService.findAverageMark(student, semester));

                    infoStudentsHeight.add(infoStudentHeight);
                    i++;
                }
                model.addAttribute("studentsHeight", infoStudentsHeight);

            }
            if (studentsLow.size()==0){
                model.addAttribute("tableLow", "nothing");
            }
            else {
                model.addAttribute("tableLow", "something");
                ArrayList<InfoForReport> infoStudentsLow = new ArrayList<>();

                int i = 1;
                for (Student student: studentsLow) {
                    InfoForReport infoStudentLow = new InfoForReport(i, studentService.findStudentName(student), markService.findAverageMark(student, semester));
                    infoStudentsLow.add(infoStudentLow);
                    i++;
                }
                model.addAttribute("studentsLow", infoStudentsLow);
            }
        }
        return "admin_report_group";
    }
    @PostMapping("/admin_report_course")
    public String adminReportCoursePost(@RequestParam("course") Integer course, @RequestParam("semester") Integer semester, Model model){
        //Потом убрать когда будет получение студентов по курсу
        Group group = groupService.findGroupByName(groupService.findGroupByStudent(studentService.findById(8)));
        if (course == 0 || semester == 0) {
            model.addAttribute("tables", "nothing");
        }
        else {
            model.addAttribute("tables", "something");
            ArrayList<Student> studentsHeight = studentService.findStudentsByGroupAndHeightMark(group, semester);
            ArrayList<Student> studentsLow = studentService.findStudentsByGroupAndLowestMark(group, semester);

            if (studentsHeight.size()==0){
                model.addAttribute("tableHeight", "nothing");
            }
            else {

                model.addAttribute("tableHeight", "something");
                ArrayList<InfoForReport> infoStudentsHeight = new ArrayList<>();
                int i = 1;
                for (Student student: studentsHeight) {
                    InfoForReport infoStudentHeight = new InfoForReport(i, facultyService.findFacultyName(facultyService.findFacultyByStudent(student)), specialityService.findSpecialityByStudents(student),
                            studentService.findStudentName(student), groupService.findGroupByStudent(student), markService.findAverageMark(student, semester));

                    infoStudentsHeight.add(infoStudentHeight);
                    i++;
                }
                model.addAttribute("studentsHeight", infoStudentsHeight);

            }
            if (studentsLow.size()==0){
                model.addAttribute("tableLow", "nothing");
            }
            else {
                model.addAttribute("tableLow", "something");
                ArrayList<InfoForReport> infoStudentsLow = new ArrayList<>();

                int i = 1;
                for (Student student: studentsLow) {
                    InfoForReport infoStudentLow = new InfoForReport(i, facultyService.findFacultyName(facultyService.findFacultyByStudent(student)), specialityService.findSpecialityByStudents(student),
                            studentService.findStudentName(student), groupService.findGroupByStudent(student), markService.findAverageMark(student, semester));

                    infoStudentsLow.add(infoStudentLow);
                    i++;
                }
                model.addAttribute("studentsLow", infoStudentsLow);
            }
        }
        return "admin_report_course";
    }
    @PostMapping("/exams_faculty")
    public String examsFacultyPost(@RequestParam("facultyName") String facultyName,@RequestParam("semester") Integer semester, Model model){
        ArrayList<String> faculties = facultyService.findFacultyName();
        model.addAttribute("faculties", faculties);
        Faculty faculty = facultyService.findFacultyByName(facultyName);
        if (facultyName.equals("--") || semester == 0) {
            model.addAttribute("table", "nothing");
        }
        else {
            model.addAttribute("table", "something");
            ArrayList<Student> students = studentService.findByFaculty(faculty);
            if (students.size()==0){
                model.addAttribute("studentsInTable", "nothing");
            }
            else {

                model.addAttribute("studentsInTable", "something");
                ArrayList<InfoForReport> infoStudents = new ArrayList<>();
                int i = 1;
                for (Student student: students) {
                    InfoForReport infoStudent = new InfoForReport(i,  studentService.findCourse(student),
                            specialityService.findSpecialityByStudents(student),studentService.findStudentName(student),
                            groupService.findGroupByStudent(student), markService.findAverageMark(student, semester));

                    infoStudents.add(infoStudent);
                    i++;
                }
                model.addAttribute("students", infoStudents);
            }
        }
        return "exams_faculty";
    }
    @PostMapping("/exams_speciality")
    public String examsSpecialityPost(@RequestParam("specialityName") String specialityName,@RequestParam("semester") Integer semester, Model model){
        ArrayList<String> specialities = specialityService.findSpecialityName();
        model.addAttribute("specialities", specialities);
        Speciality speciality = specialityService.findSpecialityByName(specialityName);
        if (specialityName.equals("--") || semester == 0) {
            model.addAttribute("table", "nothing");
        }
        else {
            model.addAttribute("table", "something");
            ArrayList<Student> students = studentService.findBySpeciality(speciality);
            if (students.size()==0){
                model.addAttribute("studentsInTable", "nothing");
            }
            else {

                model.addAttribute("studentsInTable", "something");
                ArrayList<InfoForReport> infoStudents = new ArrayList<>();
                int i = 1;
                for (Student student: students) {
                    InfoForReport infoStudent = new InfoForReport(i, facultyService.findFacultyName(facultyService.findFacultyByStudent(student)), studentService.findCourse(student),
                            studentService.findStudentName(student),
                            groupService.findGroupByStudent(student), markService.findAverageMark(student, semester));

                    infoStudents.add(infoStudent);
                    i++;
                }
                model.addAttribute("students", infoStudents);
            }
        }
        return "exams_speciality";
    }
    @PostMapping("/exams_course")
    public String examsCoursePost(@RequestParam("course") Integer course,@RequestParam("semester") Integer semester, Model model){

        if (course == 0 || semester == 0) {
            model.addAttribute("table", "nothing");
        }
        else {
            model.addAttribute("table", "something");
            ArrayList<Student> students = studentService.findByCourse(course);
            if (students.size()==0){
                model.addAttribute("studentsInTable", "nothing");
            }
            else {
                model.addAttribute("studentsInTable", "something");
                ArrayList<InfoForReport> infoStudents = new ArrayList<>();
                int i = 1;
                for (Student student: students) {
                    InfoForReport infoStudent = new InfoForReport(i, facultyService.findFacultyName(facultyService.findFacultyByStudent(student)), specialityService.findSpecialityByStudents(student),
                            studentService.findStudentName(student), groupService.findGroupByStudent(student),
                            markService.findAverageMark(student, semester));
                    infoStudents.add(infoStudent);
                    i++;
                }
                model.addAttribute("students", infoStudents);
            }
        }
        return "exams_course";
    }

    @PostMapping("/exams_group")
    public String examsCoursePost(@RequestParam("groupName") String groupName,@RequestParam("semester") Integer semester, Model model){

        ArrayList<String> groups = groupService.findGroupName();
        model.addAttribute("groups", groups);
        Group group = groupService.findGroupByName(groupName);
        if (groupName.equals("--") || semester == 0) {
            model.addAttribute("table", "nothing");
        }
        else {
            model.addAttribute("table", "something");
            ArrayList<Student> students = studentService.findByGroup(group);
            if (students.size()==0){
                model.addAttribute("studentsInTable", "nothing");
            }
            else {
                model.addAttribute("studentsInTable", "something");
                ArrayList<InfoForReport> infoStudents = new ArrayList<>();
                int i = 1;
                for (Student student: students) {
                    InfoForReport infoStudent = new InfoForReport(i,
                            studentService.findStudentName(student), markService.findAverageMark(student, semester));
                    infoStudents.add(infoStudent);
                    i++;
                }
                model.addAttribute("students", infoStudents);
            }
        }
        return "exams_group";
    }
    @PostMapping("/admin_teacher_page")
    public String adminTeacherPagePost(@RequestParam("teacherName") String teacherName, Model model){

        String[] teacherSurname = teacherName.split("\\s");
        Teacher teacher = teacherService.findTeacherBySurname(teacherSurname[0]);
        if ( teacher == null){
            model.addAttribute("teacherInfo", "nothing");
        }
        else {

            model.addAttribute("teacherInfo", "something");
            String teacherFullName = teacherService.findTeacherFullName(teacher);
            String teacherLogin = teacherService.findTeacherLogin(teacher);
            String teacherPassword = teacherService.findTeacherPassword(teacher);
            model.addAttribute("teacherFullName", teacherFullName);
            model.addAttribute("teacherLogin", teacherLogin);
            model.addAttribute("teacherPassword", teacherPassword);
            ArrayList<Subject> teacherSubjects = subjectService.findSubjectsByTeacher(teacher);
            if (teacherSubjects == null){
                model.addAttribute("teacherIfExistSubjects", "nothing");
            }else {
                model.addAttribute("teacherIfExistSubjects", "something");
                ArrayList<String> teacherSubjectsNames = new ArrayList<>();
                for (Subject subject:teacherSubjects){
                    teacherSubjectsNames.add(subjectService.findSubjectName(subject));
                }
                model.addAttribute("teacherSubjectsNames", teacherSubjectsNames);
            }

        }
        return "admin_teacher_page";
    }
    @GetMapping("/admin_teacher_page_subject_info/{subjectName}")
    public String adminTeacherPageSubjectInfo(@PathVariable(value = "subjectName") String subjectName, Model model){
        ArrayList<Group> groups = groupService.findGroupBySubject(subjectService.findSubjectByName(subjectName));
        ArrayList<String> groupNames = new ArrayList<>();
        for (Group group: groups) {
            groupNames.add(groupService.findGroupName(group));
        }
        model.addAttribute("groups", groupNames);
        model.addAttribute("subjectName", subjectName);
        return "admin_teacher_page_subject_info";
    }
    @PostMapping("/admin_teacher_page_subject_info/{subjectName}")
    public String adminTeacherPageSubjectInfoPost(@PathVariable(value = "subjectName") String subjectName,
                                                  @RequestParam("groupName") String groupName, Model model){
        System.out.println(subjectName);
        model.addAttribute("subjectName", subjectName);
        ArrayList<Group> groups = groupService.findGroupBySubject(subjectService.findSubjectByName(subjectName));
        ArrayList<String> groupNames = new ArrayList<>();
        for (Group group: groups) {
            groupNames.add(groupService.findGroupName(group));
        }
        model.addAttribute("groups", groupNames);
        model.addAttribute("subjectName", subjectName);
        if (groupName.equals("--")) {
            model.addAttribute("subjectGroupInfo", "nothing");
        }
        else {
            model.addAttribute("subjectGroupInfo", "something");
            Group group = groupService.findGroupByName(groupName);
            String facultyName = facultyService.findFacultyName(facultyService.findFacultyByGroup(group));
            String testType = subjectService.findTestTypeBySubject(subjectService.findSubjectByName(subjectName));
            model.addAttribute("subjectGroupInfoFaculty", facultyName);
            model.addAttribute("subjectGroupInfoGroup", groupName);
            model.addAttribute("subjectGroupInfoTestType", testType);

            ArrayList<Student> students = studentService.findByGroup(group);
            ArrayList<InfoForReport> infoStudents = new ArrayList<>();
            int i = 1;
            for (Student student: students) {
                InfoForReport infoStudent = new InfoForReport(i,
                        studentService.findStudentName(student), markService.findMarkByStudentAndSubject(student, subjectService.findSubjectByName(subjectName)));
                infoStudents.add(infoStudent);
                i++;
            }
            model.addAttribute("subjectGroupInfoStudents", infoStudents);

        }

        return "admin_teacher_page_subject_info";
    }

}
