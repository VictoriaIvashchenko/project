package com.assessing.project.controllers;

import com.assessing.project.additional.InfoForReport;
import com.assessing.project.additional.InfoForStudentPage;
import com.assessing.project.config.SecurityUser;
import com.assessing.project.model.entity.*;
import com.assessing.project.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
    Admin admin = new Admin();

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("title", "Сторінка адміністратора");
        //видобуток поточного адміністратора після авторизації
        SecurityUser curr = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Admin currentAdmin = adminService.findByLogin(curr.getUsername());
        admin = currentAdmin;
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "admin";
    }

    @GetMapping("/admin_teacher_page")
    public String adminTeacherPage(Model model){
        model.addAttribute("title", "Сторінка інформаці про викладача");
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "admin_teacher_page";
    }

    @GetMapping("/admin_teacher_page_add_new_subject/{teacherNameAddNew}")
    public String adminAddSubject(@PathVariable("teacherNameAddNew") String teacherName, Model model){
        model.addAttribute("title", "Додати новий предмет");
        model.addAttribute("teacherNameAddNew", teacherName);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "admin_teacher_page_add_new_subject";
    }

    @GetMapping("/admin_teacher_page_subject_info")
    public String adminInfoSubject(Model model){
        model.addAttribute("title", "Іформація про предмет");
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "admin_teacher_page_subject_info";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("title", "Додати користувача");
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "add";
    }
    @GetMapping("/add_admin")
    public String addAdmin(Model model){
        model.addAttribute("title", "Додати адміністратора");
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
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
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "add_student";
    }
    @GetMapping("/add_teacher")
    public String addTeacher(Model model){
        model.addAttribute("title", "Додати викладача");
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "add_teacher";
    }

    @GetMapping("/exams")
    public String exams(Model model){
        model.addAttribute("title", "Результати сесії");
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "exams";
    }
    @GetMapping("/exams_faculty")
    public String examsFaculty(Model model){
        model.addAttribute("title", "Результати сесії по факультету");
        ArrayList<String> faculties = facultyService.findFacultyName();
        model.addAttribute("faculties", faculties);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "exams_faculty";
    }
    @GetMapping("/exams_speciality")
    public String examsSpeciality(Model model){
        model.addAttribute("title", "Результати сесії по спецільності");
        ArrayList<String> specialities = specialityService.findSpecialityName();
        model.addAttribute("specialities", specialities);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "exams_speciality";
    }
    @GetMapping("/exams_course")
    public String examsCourse(Model model){
        model.addAttribute("title", "Результати сесії по курсу");
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "exams_course";
    }
    @GetMapping("/exams_group")
    public String examsGroup(Model model){
        model.addAttribute("title", "Результати сесії по групі");
        ArrayList<String> groups = groupService.findGroupName();
        model.addAttribute("groups", groups);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "exams_group";
    }
    @GetMapping("/admin_report")
    public String adminReport(Model model){
        model.addAttribute("title", "Звіти");
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "admin_report";
    }
    @GetMapping("/admin_report_faculty")
    public String adminReportFaculty(Model model){
        model.addAttribute("title", "Звіти по факультету");
        ArrayList<String> faculties = facultyService.findFacultyName();
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        model.addAttribute("faculties", faculties);
        return "admin_report_faculty";
    }
    @GetMapping("/admin_report_speciality")
    public String adminReportSpeciality(Model model){
        model.addAttribute("title", "Звіти по спецільності");
        ArrayList<String> specialities = specialityService.findSpecialityName();
        model.addAttribute("specialities", specialities);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "admin_report_speciality";
    }

    @GetMapping("/admin_report_group")
    public String adminReportGroup(Model model){
        model.addAttribute("title", "Звіти по групі");
        ArrayList<String> groups = groupService.findGroupName();
        model.addAttribute("groups", groups);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "admin_report_group";
    }
    @GetMapping("/adddata")
    public String adddata(Model model){
        model.addAttribute("title", "Додати дані");
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "adddata";
    }
    @GetMapping("/adddata_faculty")
    public String adddataFaculty(Model model){
        model.addAttribute("title", "Додати новий факультет");
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "adddata_faculty";
    }

    @GetMapping("/adddata_speciality")
    public String adddataSpeciality(Model model){
        model.addAttribute("title", "Додати нову спеціальність");
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "adddata_speciality";
    }

    @GetMapping("/adddata_group")
    public String adddataGroup(Model model){
        model.addAttribute("title", "Додати нову групу");

        ArrayList<String> faculties = facultyService.findFacultyName();
        model.addAttribute("faculties", faculties);
        ArrayList<String> specialities = specialityService.findSpecialityName();
        model.addAttribute("specialities", specialities);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "adddata_group";
    }
    @PostMapping("/adddata_faculty")
    public String addFaculty(@RequestParam("name") String name, Model model){
        facultyService.create(name);

        return "redirect:/adddata";
    }
    @PostMapping("/adddata_speciality")
    public String addSpeciality(@RequestParam("name") String name, Model model){
        specialityService.create(name);
        return "redirect:/adddata";
    }
    @PostMapping("/adddata_group")
    public String addGroup(@RequestParam("name") String name, @RequestParam("facultyName") String facultyName,
                           @RequestParam("specialityName") String specialityName, Model model){

        groupService.create(name, facultyService.findFacultyByName(facultyName),
                specialityService.findSpecialityByName(specialityName));
        return "redirect:/adddata";
    }

    @PostMapping("/add_admin")
    public String addAdminPOST(@RequestParam("name") String name, @RequestParam("surname") String surname,
                               @RequestParam("patronymic") String patronymic, @RequestParam("login") String login,
                               @RequestParam("password") String password, Model model){

        adminService.create(name, surname, patronymic, login, new BCryptPasswordEncoder(12).encode(password));
        return"redirect:/add";
    }
    @PostMapping("/add_teacher")
    public String addTeacherPOST(@RequestParam("name") String name, @RequestParam("surname") String surname,
                                 @RequestParam("patronymic") String patronymic, @RequestParam("login") String login,
                                 @RequestParam("password") String password, Model model){

        teacherService.create(surname, name, patronymic, login, new BCryptPasswordEncoder(12).encode(password));
        return"redirect:/add";
    }
    @PostMapping("/add_student")
    public String addStudentPOST(@RequestParam("name") String name, @RequestParam("surname") String surname,
                                 @RequestParam("patronymic") String patronymic,@RequestParam("groupName") String groupName,
                                 @RequestParam("facultyName") String facultyName, @RequestParam("specialityName") String specialityName,
                                 @RequestParam("course") Integer course, @RequestParam("login") String login,
                                 @RequestParam("password") String password, Model model){


        studentService.create(surname, name, patronymic, facultyService.findFacultyByName(facultyName),
                specialityService.findSpecialityByName(specialityName),groupService.findGroupByName(groupName),course,
                login, new BCryptPasswordEncoder(12).encode(password));
        return"redirect:/add";
    }
    @PostMapping("/admin_teacher_page_add_new_subject/{teacherNameAddNew}")
    public String adminAddSubjectPost(@PathVariable("teacherNameAddNew") String teacherName,
                                      @RequestParam("name") String name, @RequestParam("testType") String testType,
                                      Model model){
        model.addAttribute("title", "Додати новий предмет");
        Teacher teacher = teacherService.findTeacherBySurname(teacherName);
        System.out.println(teacherService.findTeacherFullName(teacher));
        subjectService.create(name, testType, teacher);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "admin_teacher_page";
    }
    @PostMapping("/admin_report_faculty")
    public String adminReportFacultyPost(@RequestParam("facultyName") String facultyName,
                                         @RequestParam("semester") Integer semester, Model model){
        ArrayList<String> faculties = facultyService.findFacultyName();
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
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
                ArrayList<InfoForReport> infoStudentsHeight = new ArrayList<>();
                int i = 1;
                for (Student student: studentsHeight) {
                    InfoForReport infoStudentHeight = new InfoForReport(i,  studentService.findCourse(student),
                            specialityService.findSpecialityByStudents(student),studentService.findStudentName(student),
                            groupService.findGroupByStudent(student), markService.findAverageMark(student, semester));

                    if (infoStudentHeight.getAverageMarkNumber() != 0.0){
                        infoStudentsHeight.add(infoStudentHeight);
                    }
                    i++;
                }
                if(infoStudentsHeight.size() != 0){
                    model.addAttribute("studentsHeight", infoStudentsHeight);
                    model.addAttribute("tableHeight", "something");

                }else {
                    model.addAttribute("tableHeight", "nothing");
                }

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
    public String adminReportSpecialityPost(@RequestParam("specialityName") String specialityName,
                                            @RequestParam("semester") Integer semester, Model model){
        ArrayList<String> specialities = specialityService.findSpecialityName();
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        model.addAttribute("specialities", specialities);
        Speciality speciality = specialityService.findSpecialityByName(specialityName);
        if (specialityName.equals("--") || semester == 0) {
            model.addAttribute("tables", "nothing");
        }
        else {
            model.addAttribute("tables", "something");
            ArrayList<Student> studentsHeight =
                    studentService.findStudentsBySpecialityAndHeightMark(speciality, semester);


            ArrayList<Student> studentsLow = studentService.findStudentsBySpecialityAndLowestMark(speciality,semester);
            if (studentsHeight.size()==0){
                model.addAttribute("tableHeight", "nothing");
            }
            else {

                ArrayList<InfoForReport> infoStudentsHeight = new ArrayList<>();
                int i = 1;
                for (Student student: studentsHeight) {
                    InfoForReport infoStudentHeight = new InfoForReport(i,
                            facultyService.findFacultyName(facultyService.findFacultyByStudent(student)),
                            studentService.findCourse(student), studentService.findStudentName(student),
                            groupService.findGroupByStudent(student), markService.findAverageMark(student, semester));

                    if (infoStudentHeight.getAverageMarkNumber() != 0.0){
                        infoStudentsHeight.add(infoStudentHeight);
                    }
                    i++;
                }
                if(infoStudentsHeight.size() != 0){
                    model.addAttribute("studentsHeight", infoStudentsHeight);
                    model.addAttribute("tableHeight", "something");

                }else {
                    model.addAttribute("tableHeight", "nothing");
                }

            }
            if (studentsLow.size()==0){
                model.addAttribute("tableLow", "nothing");
            }
            else {
                model.addAttribute("tableLow", "something");
                ArrayList<InfoForReport> infoStudentsLow = new ArrayList<>();

                int i = 1;
                for (Student student: studentsLow) {
                    InfoForReport infoStudentLow = new InfoForReport(i,
                            facultyService.findFacultyName(facultyService.findFacultyByStudent(student)),
                            studentService.findCourse(student), studentService.findStudentName(student),
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
    public String adminReportGroupPost(@RequestParam("groupName") String groupName,
                                       @RequestParam("semester") Integer semester, Model model){
        ArrayList<String> groups = groupService.findGroupName();
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
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

                ArrayList<InfoForReport> infoStudentsHeight = new ArrayList<>();
                int i = 1;
                for (Student student: studentsHeight) {
                    InfoForReport infoStudentHeight = new InfoForReport(i,
                            studentService.findStudentName(student), markService.findAverageMark(student, semester));

                    if (infoStudentHeight.getAverageMarkNumber() != 0.0){
                        infoStudentsHeight.add(infoStudentHeight);
                    }
                    i++;
                }
                if(infoStudentsHeight.size() != 0){
                    model.addAttribute("studentsHeight", infoStudentsHeight);
                    model.addAttribute("tableHeight", "something");

                }else {
                    model.addAttribute("tableHeight", "nothing");
                }

            }
            if (studentsLow.size()==0){
                model.addAttribute("tableLow", "nothing");
            }
            else {
                model.addAttribute("tableLow", "something");
                ArrayList<InfoForReport> infoStudentsLow = new ArrayList<>();

                int i = 1;
                for (Student student: studentsLow) {
                    InfoForReport infoStudentLow = new InfoForReport(i, studentService.findStudentName(student),
                            markService.findAverageMark(student, semester));
                    infoStudentsLow.add(infoStudentLow);
                    i++;
                }
                model.addAttribute("studentsLow", infoStudentsLow);
            }
        }
        return "admin_report_group";
    }
//
    @PostMapping("/exams_faculty")
    public String examsFacultyPost(@RequestParam("facultyName") String facultyName,
                                   @RequestParam("semester") Integer semester, Model model){
        ArrayList<String> faculties = facultyService.findFacultyName();
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
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
    public String examsSpecialityPost(@RequestParam("specialityName") String specialityName,
                                      @RequestParam("semester") Integer semester, Model model){
        ArrayList<String> specialities = specialityService.findSpecialityName();
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
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
                    InfoForReport infoStudent = new InfoForReport(i,
                            facultyService.findFacultyName(facultyService.findFacultyByStudent(student)),
                            studentService.findCourse(student), studentService.findStudentName(student),
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
    public String examsCoursePost(@RequestParam("course") Integer course,
                                  @RequestParam("semester") Integer semester, Model model){
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
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
                    InfoForReport infoStudent = new InfoForReport(i,
                            facultyService.findFacultyName(facultyService.findFacultyByStudent(student)),
                            specialityService.findSpecialityByStudents(student), studentService.findStudentName(student),
                            groupService.findGroupByStudent(student),
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
    public String examsCoursePost(@RequestParam("groupName") String groupName,
                                  @RequestParam("semester") Integer semester, Model model){
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
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
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        String[] teacherSurname = teacherName.split("\\s");
        Teacher teacher = teacherService.findTeacherBySurname(teacherSurname[0]);
        if ( teacher == null){
            model.addAttribute("teacherInfo", "nothing");
        }
        else {
            model.addAttribute("teacherNameAddNew", teacherSurname[0]);
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

    @GetMapping("/admin_teacher_subject_add_group/{subjectName}")
    public String adminTeacherPageSubjectAddGroup(@PathVariable(value = "subjectName") String subjectName, Model model){
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        List<Group> groups = groupService.findAllGroups();
        ArrayList<String> groupNames = new ArrayList<>();
        for (Group group: groups) {
            groupNames.add(groupService.findGroupName(group));
        }
        model.addAttribute("title", "Додати групу");
        model.addAttribute("groups", groupNames);
        model.addAttribute("subjectName", subjectName);
        return "admin_teacher_subject_add_group";
    }
    @PostMapping("/admin_teacher_subject_add_group/{subjectName}")
    public String adminTeacherPageSubjectAddGroupPost(@PathVariable(value = "subjectName") String subjectName,
                                                      @RequestParam("groupName") String groupName, Model model){
        System.out.println(groupName);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "redirect:/admin_teacher_page_subject_info/{subjectName}";
    }
    @GetMapping("/admin_teacher_page_subject_info/{subjectName}")
    public String adminTeacherPageSubjectInfo(@PathVariable(value = "subjectName") String subjectName, Model model){
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        ArrayList<Group> groups = groupService.findGroupBySubject(subjectService.findSubjectByName(subjectName));
        ArrayList<String> groupNames = new ArrayList<>();
        for (Group group: groups) {
            groupNames.add(groupService.findGroupName(group));
        }
        model.addAttribute("title", "Дисціпліна");
        model.addAttribute("groups", groupNames);
        model.addAttribute("subjectName", subjectName);
        return "admin_teacher_page_subject_info";
    }
    @PostMapping("/admin_teacher_page_subject_info/{subjectName}")
    public String adminTeacherPageSubjectInfoPost(@PathVariable(value = "subjectName") String subjectName,
                                                  @RequestParam("groupName") String groupName, Model model){
        System.out.println(subjectName);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
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
                        studentService.findStudentName(student), markService.markGetIntegerValue(markService.findMarkByStudentAndSubject(student,
                        subjectService.findSubjectByName(subjectName))));

                infoStudents.add(infoStudent);
                i++;
            }
            model.addAttribute("subjectGroupInfoStudents", infoStudents);

        }

        return "admin_teacher_page_subject_info";
    }
    @GetMapping("/edit_teacher/{teacherFullName}")
    public String editTeacher(@PathVariable(value="teacherFullName") String teacherFullName, Model model){
        model.addAttribute("teacherFullName", teacherFullName);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        model.addAttribute("title", "Редагування даних викладача");
        String[] teacher = teacherFullName.split("\\s");
        model.addAttribute("teacherName", teacherFullName);
//        Teacher teacher = teacherService.findTeacherBySurname(teacherSurname[0]);

        String teacherLogin = teacherService.findTeacherLogin(teacherService.findTeacherBySurname(teacher[0]));
        String teacherPassword = teacherService.findTeacherPassword(teacherService.findTeacherBySurname(teacher[0]));

        model.addAttribute("name", teacher[1]);
        model.addAttribute("surname", teacher[0]);
        model.addAttribute("patronymic", teacher[2]);
        model.addAttribute("login", teacherLogin);
        model.addAttribute("password", "");
        return "edit_teacher";
    }
    @PostMapping("/edit_teacher/{teacherFullName}")
    public String editTeacherPost(@PathVariable(value="teacherFullName") String teacherFullName,@RequestParam("name") String name,
                                  @RequestParam("surname") String surname, @RequestParam("patronymic") String patronymic,
                                  @RequestParam("login") String login, @RequestParam("password") String password, Model model){
        String[] teacherFullNameArr = teacherFullName.split("\\s");
        Teacher teacher = teacherService.findTeacherBySurname(teacherFullNameArr[0]);
        teacher.setSurname(surname);
        teacher.setName(name);
        teacher.setPatronymic(patronymic);
        teacher.setLogin(login);
        if (!password.equals("")){
            teacher.setPassword(password);
        }
        teacherService.update(teacher);
        return "redirect:/admin_teacher_page";
    }
    @GetMapping("/admin_student_page")
    public String adminStudentPage(Model model){
        model.addAttribute("title", "Сторінка інформаці про студента");
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "admin_student_page";
    }
    @PostMapping("/admin_student_page")
    public String adminStudentPagePost( @RequestParam("studentName") String studentName, Model model){
        model.addAttribute("adminName", adminService.findAdminFullName(admin));

        //Для пошуку студента потрібно додати метод у сервіс
        Student student = studentService.findStudentByLogin("lera");
        if ( student == null){
            model.addAttribute("studentInfo", "nothing");
        }

        else {

            model.addAttribute("studentInfo", "something");
            String[] studentFullName = studentService.findStudentName(student);
            String  studentFullNameString = "";
            for (String s:studentFullName) {
                studentFullNameString += s ;
                studentFullNameString += " " ;
            }
            //додати методи у сервіс
            String studentLogin = "login";

            model.addAttribute("studentFullName", studentFullNameString);
            model.addAttribute("studentLogin", studentLogin);


            String facultyName = facultyService.findFacultyName(facultyService.findFacultyByStudent(student));
            String specialityName = specialityService.findSpecialityByStudents(student);
            String groupName = groupService.findGroupByStudent(student);
            Integer course = studentService.findCourse(student);

            model.addAttribute("facultyName", facultyName);
            model.addAttribute("specialityName", specialityName);
            model.addAttribute("course", course);
            model.addAttribute("groupName", groupName);

            List<Mark> marks1 = markService.findByStudentAndSemester(student, 1);
            if (marks1.size() == 0){
                model.addAttribute("table1", "nothing");
            }
            else {
                ArrayList<InfoForStudentPage> rows = new ArrayList<>();
                int i = 0;
                for (Mark mark: marks1) {
                    System.out.println(subjectService.findSubjectNameByMark(mark));
                    InfoForStudentPage row = new InfoForStudentPage(i+1,teacherService.findTeacherNameByMark(mark),
                            subjectService.findSubjectNameByMark(mark), subjectService.findTestTypeByMark(mark), markService.findMarkValue(mark));
                    rows.add(row);
                    i++;
                }
                Double average = markService.findAverageMark(student, 1);
                model.addAttribute("marksTable1", rows);
                model.addAttribute("average1", new DecimalFormat("#.##").format(average));
                model.addAttribute("table1", "something");
            }
            List<Mark> marks2 = markService.findByStudentAndSemester(student, 1);
            if (marks2.size() == 0){
                model.addAttribute("table2", "nothing");
            }
            else {
                ArrayList<InfoForStudentPage> rows = new ArrayList<>();
                int i = 0;
                for (Mark mark: marks2) {
                    System.out.println(subjectService.findSubjectNameByMark(mark));
                    InfoForStudentPage row = new InfoForStudentPage(i+1,teacherService.findTeacherNameByMark(mark),
                            subjectService.findSubjectNameByMark(mark), subjectService.findTestTypeByMark(mark), markService.findMarkValue(mark));
                    rows.add(row);
                    i++;
                }
                Double average = markService.findAverageMark(student, 1);
                model.addAttribute("marksTable2", rows);
                model.addAttribute("average2", new DecimalFormat("#.##").format(average));
                model.addAttribute("table2", "something");
            }
        }
        return "admin_student_page";
    }
    @GetMapping("/edit_student/{studentFullName}")
    public String editStudent(@PathVariable(value="studentFullName") String teacherFullName, Model model){
        model.addAttribute("teacherFullName", teacherFullName);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        model.addAttribute("title", "Редагування даних студента");
        ArrayList<String> faculties = facultyService.findFacultyName();
        model.addAttribute("faculties", faculties);
        ArrayList<String> groups = groupService.findGroupName();
        model.addAttribute("groups", groups);
        ArrayList<String> specialities = specialityService.findSpecialityName();
        model.addAttribute("specialities", specialities);
        Student student = studentService.findStudentByLogin("lera");

        String[] studentFullName = studentService.findStudentName(student);
        String  studentFullNameString = "";
        for (String s:studentFullName) {
            studentFullNameString += s ;
            studentFullNameString += " " ;
        }

        //метод получения логина студента
        String studentLogin = "login";
        model.addAttribute("studentFullName", studentFullNameString);


        String facultyName = facultyService.findFacultyName(facultyService.findFacultyByStudent(student));
        String specialityName = specialityService.findSpecialityByStudents(student);
        String groupName = groupService.findGroupByStudent(student);
//        Integer course = studentService.findCourse(student);

        int[] courses = new int[]{1, 2, 3, 4, 5,6};
        model.addAttribute("courses", courses);
        model.addAttribute("facultyCurrent", facultyName);
        model.addAttribute("specialityCurrent", specialityName);
        model.addAttribute("courseCurrent", 1);
        model.addAttribute("groupCurrent", groupName);
        model.addAttribute("name", studentFullName[1]);
        model.addAttribute("surname", studentFullName[0]);
        model.addAttribute("patronymic", studentFullName[2]);
        model.addAttribute("login", studentLogin);
        return "edit_student";
    }
    @PostMapping("/edit_student/{studentFullName}")
    public String editStudentPost(@PathVariable(value="studentFullName") String teacherFullName,@RequestParam("name") String name, @RequestParam("surname") String surname,
                                  @RequestParam("patronymic") String patronymic,@RequestParam("groupName") String groupName,
                                  @RequestParam("facultyName") String facultyName, @RequestParam("specialityName") String specialityName,
                                  @RequestParam("course") Integer course, @RequestParam("login") String login,
                                  @RequestParam("password") String password, Model model){
        Student student = studentService.findStudentByLogin("lera");
        student.setSurname(surname);
        student.setName(name);
        student.setPatronymic(patronymic);
        student.setFaculty(facultyService.findFacultyByName(facultyName));
        student.setCourse(course);
        student.setSpeciality(specialityService.findSpecialityByName(specialityName));
        student.setGroup(groupService.findGroupByName(groupName));
        student.setLogin(login);
        if (!password.isEmpty()){
            student.setPassword(password);
        }
        studentService.update(student);
        return "redirect:/admin_student_page";
    }
    @PostMapping("/admin_student_page/{studentFullName}/delete")
    public String deleteStudent(@PathVariable("studentFullName") String studentFullName){
//        Student student = studentService.findStudentByLogin("lera");
//        studentService.delete(student);
        System.out.println("Студента видалено");
        return "admin_student_page";
    }
    @PostMapping("/admin_teacher_page/{teacherFullName}/delete")
    public String deleteTeacher(@PathVariable("teacherFullName") String teacherFullName){
        Teacher teacher = teacherService.findTeacherBySurname("П");
        teacherService.delete(teacher);
        System.out.println("Викладача видалено");
        return "admin_teacher_page";
    }

}
