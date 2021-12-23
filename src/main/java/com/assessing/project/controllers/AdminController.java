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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @Autowired
    SubjectGroupService subjectGroupService;
    Admin admin = new Admin();

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("title", "Сторінка адміністратора");
        //видобуток поточного адміністратора після авторизації
        SecurityUser curr = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        admin = adminService.findByLogin(curr.getUsername());
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "admin";
    }

    @GetMapping("/admin_teacher_page")
    public String adminTeacherPage(Model model){
        model.addAttribute("title", "Сторінка інформаці про викладача");
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "admin_teacher_page";
    }

    @GetMapping("/admin_teacher_page_add_new_subject/{teacherId}")
    public String adminAddSubject(@PathVariable("teacherId") Integer id, Model model){
        model.addAttribute("teacherId", id);
        model.addAttribute("title", "Додати новий предмет");
        model.addAttribute("teacherId", id);
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
    public String addFaculty(@RequestParam("name") String name){
        if (!name.isEmpty()){
            ArrayList<String> faculties = facultyService.findFacultyName();

            if (faculties.contains(name)){
                System.out.println("Такий факутьте вже існує");
            }
            else {

                facultyService.create(name);
            }
        }
        return "redirect:/adddata";
    }
    @PostMapping("/adddata_speciality")
    public String addSpeciality(@RequestParam("name") String name){

        if (!name.isEmpty()){
            ArrayList<String> specialities = specialityService.findSpecialityName();
            if (specialities.contains(name)){
                System.out.println("Така спеціальність вже існує");
            }
            else {
                specialityService.create(name);
            }
        }

        return "redirect:/adddata";
    }
    @PostMapping("/adddata_group")
    public String addGroup(@RequestParam("name") String name, @RequestParam("facultyName") String facultyName,
                           @RequestParam("specialityName") String specialityName){
        if (!name.isEmpty() & !facultyName.isEmpty() & !specialityName.isEmpty()){
            ArrayList<String> groups = groupService.findGroupName();
            if (groups.contains(name)){
                System.out.println("Така група вже існує");
            }
            else {
                groupService.create(name, facultyService.findFacultyByName(facultyName),
                        specialityService.findSpecialityByName(specialityName));
            }
        }
        return "redirect:/adddata";
    }

    @PostMapping("/add_admin")
    public String addAdminPOST(@RequestParam("name") String name, @RequestParam("surname") String surname,
                               @RequestParam("patronymic") String patronymic, @RequestParam("login") String login,
                               @RequestParam("password") String password){
        if (!name.isEmpty() & !surname.isEmpty() & !patronymic.isEmpty() & !login.isEmpty() & !password.isEmpty()){
            if (adminService.findByLogin(login) != null){
                System.out.println("Такий админ вже існує");
            }
            else{
                adminService.create(name, surname, patronymic, login, new BCryptPasswordEncoder(12).encode(password));
            }
        }

        return"redirect:/add";
    }
    @PostMapping("/add_teacher")
    public String addTeacherPOST(@RequestParam("name") String name, @RequestParam("surname") String surname,
                                 @RequestParam("patronymic") String patronymic, @RequestParam("login") String login,
                                 @RequestParam("password") String password){

        if (!name.isEmpty() & !surname.isEmpty() & !patronymic.isEmpty() & !login.isEmpty() & !password.isEmpty()){
            if (teacherService.findTeacherByLogin(login) != null){
                System.out.println("Такий вчитель вже існує");
            }
            else{
                teacherService.create(surname, name, patronymic, login, new BCryptPasswordEncoder(12).encode(password));
            }
        }

        return"redirect:/add";
    }
    @PostMapping("/add_student")
    public String addStudentPOST(@RequestParam("name") String name, @RequestParam("surname") String surname,
                                 @RequestParam("patronymic") String patronymic,@RequestParam("groupName") String groupName,
                                 @RequestParam("facultyName") String facultyName, @RequestParam("specialityName") String specialityName,
                                 @RequestParam("course") Integer course, @RequestParam("login") String login,
                                 @RequestParam("password") String password){

        if (!name.isEmpty() & !surname.isEmpty() & !patronymic.isEmpty() & !login.isEmpty() & !password.isEmpty()
                & !facultyName.equals("--") & !groupName.equals("--") & !specialityName.equals("--") & course != 0){
            if (studentService.findStudentByLogin(login) != null){
                System.out.println("Такий студент вже існує");
            }
            else{
                studentService.create(surname, name, patronymic, facultyService.findFacultyByName(facultyName),
                        specialityService.findSpecialityByName(specialityName),groupService.findGroupByName(groupName),course,
                        login, new BCryptPasswordEncoder(12).encode(password));
            }
        }
        return"redirect:/add";
    }
    @PostMapping("/admin_teacher_page_add_new_subject/{teacherId}")
    public String adminAddSubjectPost(@PathVariable("teacherId") Integer id,
                                      @RequestParam("name") String name, @RequestParam("testType") String testType,
                                      Model model){
        model.addAttribute("title", "Додати новий предмет");
        model.addAttribute("adminName", adminService.findAdminFullName(admin));

        Teacher teacher = teacherService.findTeacherById(id);
        ArrayList<Subject> subjects = subjectService.findSubjectsByTeacher(teacher);
        ArrayList<String> subjectsName = new ArrayList<>();
        for (Subject subject: subjects){
            subjectsName.add(subjectService.findSubjectName(subject));
        }
        if (!name.isEmpty() & !testType.isEmpty()){
            if (subjectsName.contains(name)){
                System.out.println("Такий предмет вже э");
            }
            else {
                subjectService.create(name, testType, teacher);
            }
        }
        return "redirect:/admin_teacher_page";
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
                    if (infoStudent.getAverageMarkNumber() !=0){
                        infoStudents.add(infoStudent);
                    }
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
                    if (infoStudent.getAverageMarkNumber() !=0){
                        infoStudents.add(infoStudent);
                    }
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
                    if (infoStudent.getAverageMarkNumber() !=0){
                        infoStudents.add(infoStudent);
                    }
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
                    if (infoStudent.getAverageMarkNumber() !=0){
                        infoStudents.add(infoStudent);
                    }
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
        String[] teacherFullNameArr = teacherName.split("\\s");
        Teacher teacher = teacherService.findTeacherBySurnameAndName(teacherFullNameArr[0], teacherFullNameArr[1]);
        if ( teacher == null){
            model.addAttribute("teacherInfo", "nothing");
        }
        else {
            model.addAttribute("teacherId", teacherService.findIdByTeacher(teacher));
            model.addAttribute("teacherInfo", "something");
            String teacherFullName = teacherService.findTeacherFullName(teacher);
            String teacherLogin = teacherService.findTeacherLogin(teacher);
            model.addAttribute("teacherFullName", teacherFullName);
            model.addAttribute("teacherLogin", teacherLogin);

            ArrayList<Subject> teacherSubjects = subjectService.findSubjectsByTeacher(teacher);
            if (teacherSubjects == null){
                model.addAttribute("teacherIfExistSubjects", "nothing");
            }else {
                model.addAttribute("teacherIfExistSubjects", "something");
                HashMap<Integer, String> teacherSubjectsNames = new HashMap<>();
                for (Subject subject:teacherSubjects){
                    teacherSubjectsNames.put(subjectService.findIdBySubject(subject), subjectService.findSubjectName(subject));
                }
                model.addAttribute("teacherSubjectsNames", teacherSubjectsNames);
            }

        }
        return "admin_teacher_page";
    }

    @GetMapping("/admin_teacher_subject_add_group/{subjectId}")
    public String adminTeacherPageSubjectAddGroup(@PathVariable(value = "subjectId") Integer id, Model model){
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        List<Group> groups = groupService.findAllGroups();
        List<Group> groupsTeacher = groupService.findGroupBySubject(subjectService.findSubjectById(id));
        ArrayList<String> groupNames = new ArrayList<>();
        for (Group group: groups) {
            if(!groupsTeacher.contains(group)){
                groupNames.add(groupService.findGroupName(group));
            }
        }
        model.addAttribute("title", "Додати групу");
        model.addAttribute("groups", groupNames);
        model.addAttribute("subjectName", subjectService.findSubjectName(subjectService.findSubjectById(id)));
        return "admin_teacher_subject_add_group";
    }
    @PostMapping("/admin_teacher_subject_add_group/{subjectId}")
    public String adminTeacherPageSubjectAddGroupPost(@PathVariable(value = "subjectId") Integer id,
                                                      @RequestParam("groupName") String groupName, Model model){

        model.addAttribute("adminName", adminService.findAdminFullName(admin));

        subjectGroupService.create(subjectService.findSubjectById(id), groupService.findGroupByName(groupName));
        return "redirect:/admin_teacher_page_subject_info/{subjectId}";
    }
    @GetMapping("/admin_teacher_page/{teacherId}/admin_teacher_page_subject_info/{subjectId}")
    public String adminTeacherPageSubjectInfo(@PathVariable(value = "teacherId") Integer teacherId,
                                              @PathVariable(value = "subjectId") Integer id, Model model){
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        ArrayList<Group> groups = groupService.findGroupBySubject(subjectService.findSubjectById(id));
        ArrayList<String> groupNames = new ArrayList<>();
        for (Group group: groups) {
            groupNames.add(groupService.findGroupName(group));
        }
        model.addAttribute("title", "Дисціпліна");
        model.addAttribute("groups", groupNames);
        model.addAttribute("subjectName", subjectService.findSubjectName(subjectService.findSubjectById(id)));
        return "admin_teacher_page_subject_info";
    }
    @PostMapping("/admin_teacher_page/{teacherId}/admin_teacher_page_subject_info/{subjectId}")
    public String adminTeacherPageSubjectInfoPost(@PathVariable(value = "teacherId") Integer teacherId,
                                                  @PathVariable(value = "subjectId") Integer id,
                                                  @RequestParam("groupName") String groupName, Model model){
        Subject subject = subjectService.findSubjectById(id);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        model.addAttribute("subjectName", subjectService.findSubjectName(subject));
        ArrayList<Group> groups = groupService.findGroupBySubject(subject);
        ArrayList<String> groupNames = new ArrayList<>();
        for (Group group: groups) {
            groupNames.add(groupService.findGroupName(group));
        }
        model.addAttribute("groups", groupNames);

        if (groupName.equals("--")) {
            model.addAttribute("subjectGroupInfo", "nothing");
        }
        else {
            model.addAttribute("subjectGroupInfo", "something");
            Group group = groupService.findGroupByName(groupName);
            String facultyName = facultyService.findFacultyName(facultyService.findFacultyByGroup(group));
            String testType = subjectService.findTestTypeBySubject(subject);
            model.addAttribute("subjectGroupInfoFaculty", facultyName);
            model.addAttribute("subjectGroupInfoGroup", groupName);
            model.addAttribute("subjectGroupInfoTestType", testType);

            ArrayList<Student> students = studentService.findByGroup(group);
            ArrayList<InfoForReport> infoStudents = new ArrayList<>();
            int i = 1;
            for (Student student: students) {
                InfoForReport infoStudent = new InfoForReport(i,
                        studentService.findStudentName(student),
                        markService.markGetIntegerValue(markService.findMarkByStudentAndSubject(student, subject)));

                infoStudents.add(infoStudent);
                i++;
            }
            model.addAttribute("subjectGroupInfoStudents", infoStudents);

        }

        return "admin_teacher_page_subject_info";
    }
    @GetMapping("/edit_teacher/{teacherId}")
    public String editTeacher(@PathVariable(value="teacherId") Integer id, Model model){
        model.addAttribute("teacherId", id);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        model.addAttribute("title", "Редагування даних викладача");


        Teacher teacher = teacherService.findTeacherById(id);
        String teacherFullName = teacherService.findTeacherFullName(teacher);
        String[] teacherFullNameArr = teacherFullName.split("\\s");
        String teacherLogin = teacherService.findTeacherLogin(teacher);

        model.addAttribute("teacherName", teacherFullName);
        model.addAttribute("name", teacherFullNameArr[1]);
        model.addAttribute("surname", teacherFullNameArr[0]);
        model.addAttribute("patronymic", teacherFullNameArr[2]);
        model.addAttribute("login", teacherLogin);

        return "edit_teacher";
    }
    @PostMapping("/edit_teacher/{teacherId}")
    public String editTeacherPost(@PathVariable(value="teacherId") Integer id,@RequestParam("name") String name,
                                  @RequestParam("surname") String surname, @RequestParam("patronymic") String patronymic,
                                  @RequestParam("login") String login, @RequestParam("password") String password){
        Teacher teacher = teacherService.findTeacherById(id);
        teacher.setSurname(surname);
        teacher.setName(name);
        teacher.setPatronymic(patronymic);
        teacher.setLogin(login);
        if (!password.isEmpty()){
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
        model.addAttribute("title", "Робота зі студентом");
        String[] studentNameArr = studentName.split("\\s");
        Student student = studentService.findStudentBySurnameAndName(studentNameArr[0], studentNameArr[1]);
        if ( student == null){
            model.addAttribute("studentInfo", "nothing");
        }

        else {
            model.addAttribute("studentId", studentService.getIdByStudent(student));
            model.addAttribute("studentInfo", "something");
            String[] studentFullName = studentService.findStudentName(student);
            StringBuilder studentFullNameString = new StringBuilder();
            for (String s:studentFullName) {
                studentFullNameString.append(s);
                studentFullNameString.append(" ");
            }
            String studentLogin = studentService.findStudentLogin(student);

            model.addAttribute("studentFullName", studentFullNameString.toString());
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
            List<Mark> marks2 = markService.findByStudentAndSemester(student, 2);
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
    @GetMapping("/edit_student/{studentId}")
    public String editStudent(@PathVariable(value="studentId") Integer id, Model model){
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        model.addAttribute("title", "Редагування даних студента");
        model.addAttribute("studentId", id);
        Student student = studentService.findById(id);
        String[] studentNameArr = studentService.findStudentName(student);

        ArrayList<String> faculties = facultyService.findFacultyName();
        model.addAttribute("faculties", faculties);
        ArrayList<String> groups = groupService.findGroupName();
        model.addAttribute("groups", groups);
        ArrayList<String> specialities = specialityService.findSpecialityName();
        model.addAttribute("specialities", specialities);

        String studentLogin = studentService.findStudentLogin(student);


        String facultyName = facultyService.findFacultyName(facultyService.findFacultyByStudent(student));
        String specialityName = specialityService.findSpecialityByStudents(student);
        String groupName = groupService.findGroupByStudent(student);
        Integer course = studentService.findCourse(student);

        ArrayList<Integer> courses = new ArrayList<>();
        for (int i = 1; i <=6; i++){
            courses.add(i);
        }

        model.addAttribute("courses", courses);
        model.addAttribute("facultyCurrent", facultyName);
        model.addAttribute("specialityCurrent", specialityName);
        model.addAttribute("courseCurrent", 1);
        model.addAttribute("groupCurrent", groupName);
        model.addAttribute("name", studentNameArr[0]);
        model.addAttribute("surname", studentNameArr[1]);
        model.addAttribute("patronymic", studentNameArr[2]);
        model.addAttribute("login", studentLogin);
        model.addAttribute("courseCurrent", course);
        return "edit_student";
    }
    @PostMapping("/edit_student/{studentId}")
    public String editStudentPost(@PathVariable(value="studentId") Integer id,@RequestParam("name") String name, @RequestParam("surname") String surname,
                                  @RequestParam("patronymic") String patronymic,@RequestParam("groupName") String groupName,
                                  @RequestParam("facultyName") String facultyName, @RequestParam("specialityName") String specialityName,
                                  @RequestParam("course") Integer course, @RequestParam("login") String login,
                                  @RequestParam("password") String password){

        if (!name.isEmpty() & !surname.isEmpty() & !patronymic.isEmpty() & !login.isEmpty() & !password.isEmpty()
                & !facultyName.equals("--") & !groupName.equals("--") & !specialityName.equals("--") & course != 0){
            Student student = studentService.findById(id);
            if (studentService.findStudentByLogin(login) != null & !studentService.findStudentLogin(student).equals(login)){
                System.out.println("Такий студент вже існує");
            }
            else{
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
            }
        }


        return "redirect:/admin_student_page";
    }
    @PostMapping("/admin_student_page/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Integer id){
        Student student = studentService.findById(id);
        studentService.delete(student);
        System.out.println("Студента видалено");
        return "redirect:/admin_student_page";
    }
    @PostMapping("/admin_teacher_page/{teacherId}/delete")
    public String deleteTeacher(@PathVariable("teacherId") Integer id){
        Teacher teacher = teacherService.findTeacherById(id);
        if (teacher!= null){
            teacherService.delete(teacher);
        }else {
            System.out.println("Викладача не знайдено");
        }

        return "redirect:/admin_teacher_page";
    }
    @GetMapping("/edit_data")
    public String editData(Model model){
        model.addAttribute("title", "Змінити дані");
        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "edit_data";
    }
    @GetMapping("/edit_data_faculty")
    public String editDataFaculty(Model model){
        model.addAttribute("title", "Змінити дані факультету");
        ArrayList<String> faculties = facultyService.findFacultyName();
        model.addAttribute("faculties", faculties);


        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "edit_data_faculty";
    }
    @PostMapping("/edit_data_faculty")
    public String editDataFacultyPost( @RequestParam("facultyName") String facultyName, Model model){
        model.addAttribute("title", "Змінити дані факультету");
        ArrayList<String> faculties = facultyService.findFacultyName();
        model.addAttribute("faculties", faculties);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));

        if (facultyName.equals("--")){
            model.addAttribute("facultyInfo", "nothing");
        }else{
            model.addAttribute("facultyInfo", "something");
            model.addAttribute("facultyName", facultyName);
        }
        return "edit_data_faculty";
    }
    @PostMapping("/edit_data_faculty/{facultyName}")
    public String editDataFacultyPostEdit(@PathVariable("facultyName") String facultyName,
                                          @RequestParam("name") String name, Model model){
        if (!name.isEmpty()){
            ArrayList<String> faculties = facultyService.findFacultyName();

            if (faculties.contains(name) & !facultyName.equals(name)){
                System.out.println("Такий факутьте вже існує");
            }
            else {
                Faculty faculty = facultyService.findFacultyByName(facultyName);
                faculty.setName(name);
                facultyService.update(faculty);
            }
        }

        return "redirect:/edit_data";

    }
    @PostMapping("/edit_data_faculty/{facultyName}/delete")
    public String editDataFacultyPostDelete(@PathVariable("facultyName") String facultyName, Model model){
        Faculty faculty = facultyService.findFacultyByName(facultyName);

        facultyService.delete(faculty);
        System.out.println("Факультет видалено");
        return "redirect:/edit_data";

    }
    @GetMapping("/edit_data_speciality")
    public String editDataSpeciality(Model model){
        model.addAttribute("title", "Змінити дані спеціальності");
        ArrayList<String> specialities = specialityService.findSpecialityName();
        model.addAttribute("specialities", specialities);


        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "edit_data_speciality";
    }
    @PostMapping("/edit_data_speciality")
    public String editDataSpecialityPost( @RequestParam("specialityName") String specialityName, Model model){
        model.addAttribute("title", "Змінити дані спеціальності");
        ArrayList<String> specialities = specialityService.findSpecialityName();
        model.addAttribute("specialities", specialities);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));

        if (specialityName.equals("--")){
            model.addAttribute("specialityInfo", "nothing");
        }else{
            model.addAttribute("specialityInfo", "something");
            model.addAttribute("specialityName", specialityName);
        }
        return "edit_data_speciality";
    }
    @PostMapping("/edit_data_speciality/{specialityName}")
    public String editDataSpecialityPostEdit(@PathVariable("specialityName") String specialityName,
                                          @RequestParam("name") String name, Model model){
        if (!name.isEmpty()){
            ArrayList<String> specialities = specialityService.findSpecialityName();

            if (specialities.contains(name) & !specialityName.equals(name)){
                System.out.println("Така спеціальність вже існує");
            }
            else {
                Speciality speciality = specialityService.findSpecialityByName(specialityName);
                speciality.setName(name);
                specialityService.update(speciality);
            }
        }

        return "redirect:/edit_data";

    }
    @PostMapping("/edit_data_speciality/{specialityName}/delete")
    public String editDataSpecialityPostDelete(@PathVariable("specialityName") String specialityName, Model model){
        Speciality speciality = specialityService.findSpecialityByName(specialityName);
        specialityService.delete(speciality);
        System.out.println("Спеціальність видалено");
        return "redirect:/edit_data";

    }
    @GetMapping("/edit_data_group")
    public String editDataGroup(Model model){
        model.addAttribute("title", "Змінити дані групи");

        ArrayList<String> groups = groupService.findGroupName();
        model.addAttribute("groups", groups);


        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "edit_data_group";
    }
    @PostMapping("/edit_data_group")
    public String editDataGroupPost( @RequestParam("groupName") String groupName, Model model){
        model.addAttribute("title", "Змінити дані групи");
        ArrayList<String> groups = groupService.findGroupName();
        model.addAttribute("groups", groups);
        model.addAttribute("adminName", adminService.findAdminFullName(admin));

        if (groupName.equals("--")){
            model.addAttribute("groupInfo", "nothing");
        }else{
            model.addAttribute("groupInfo", "something");
            model.addAttribute("groupName", groupName);
            ArrayList<String> faculties = facultyService.findFacultyName();
            model.addAttribute("faculties", faculties);
            ArrayList<String> specialities = specialityService.findSpecialityName();
            model.addAttribute("specialities", specialities);
            Faculty faculty =  facultyService.findFacultyByGroup(groupService.findGroupByName(groupName));
            String speciality = specialityService.findSpecialityByGroup(groupService.findGroupByName(groupName));
            model.addAttribute("currentFaculty", facultyService.findFacultyName(faculty));
            model.addAttribute("currentSpeciality", speciality);
        }
        return "edit_data_group";
    }
    @PostMapping("/edit_data_group/{groupName}")
    public String editDataGroupPostEdit(@PathVariable("groupName") String groupName, @RequestParam("name") String name,
                                        @RequestParam("facultyName") String facultyName, @RequestParam("specialityName") String specialityName){
        if (!name.isEmpty() & !facultyName.isEmpty() & !specialityName.isEmpty()){
            ArrayList<String> groups = groupService.findGroupName();

            if (groups.contains(name) & !groupName.equals(name)){
                System.out.println("Така група вже існує");
            }
            else {
                Speciality speciality = specialityService.findSpecialityByName(specialityName);
                Faculty faculty = facultyService.findFacultyByName(facultyName);
                Group group = groupService.findGroupByName(groupName);
                group.setName(name);
                group.setFaculty(faculty);
                group.setSpeciality(speciality);
                groupService.update(group);
            }
        }
        return "redirect:/edit_data";
    }
    @PostMapping("/edit_data_group/{groupName}/delete")
    public String editDataGroupPostDelete(@PathVariable("groupName") String groupName, Model model){
        Group group = groupService.findGroupByName(groupName);
        groupService.delete(group);
        System.out.println("групy видалено");
        return "redirect:/edit_data";

    }
    @GetMapping("/admin_teacher_page/{teacherId}/edit_subject/{subjectId}")
    public String editSubject(@PathVariable("teacherId") Integer teacherId,
                              @PathVariable("subjectId") Integer id,Model model){
        model.addAttribute("title", "Змінити дані групи");
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("subjectId", id);

        Subject subject = subjectService.findSubjectById(id);
        String subjectName = subjectService.findSubjectName(subject);
        ArrayList<String> testTypes = new ArrayList<String>();
        testTypes.add("Екзамен");
        testTypes.add("Залік");

        model.addAttribute("subjectName", subjectName);
        model.addAttribute("testTypes", testTypes);
        model.addAttribute("currentTestType", subjectService.findTestTypeBySubject(subject));

        model.addAttribute("adminName", adminService.findAdminFullName(admin));
        return "edit_subject";
    }
    @PostMapping("/admin_teacher_page/{teacherId}/edit_subject/{subjectId}")
    public String editSubjectPost(@PathVariable("teacherId") Integer teacherId,
                              @PathVariable("subjectId") Integer id, @RequestParam("name") String name,
                                  @RequestParam("testType") String testType, Model model){

        model.addAttribute("teacherId", teacherId);
        model.addAttribute("subjectId", id);
        if (!name.isEmpty()  & !testType.isEmpty()){
            ArrayList<Subject> subjects = subjectService.findSubjectsByTeacher(teacherService.findTeacherById(teacherId));
            ArrayList<String> subjectsNames = new ArrayList<>();
            for (Subject subject: subjects){
                subjectsNames.add(subjectService.findSubjectName(subject));
            }
            Subject subject = subjectService.findSubjectById(id);
            String subjectName = subjectService.findSubjectName(subject);

            if (subjectsNames.contains(name) & !subjectName.equals(name)){
                System.out.println("Така дисципліна вже існує");
            }
            else {
                subject.setName(name);
                subject.setTest_type(testType);
                subjectService.update(subject);
            }
        }
        return "redirect:/admin_teacher_page/{teacherId}/admin_teacher_page_subject_info/{subjectId}";
    }
    @PostMapping("/admin_teacher_page_subject_info/{subjectId}/delete")
    public String deliteSubject(@PathVariable("subjectId") Integer id, Model model){

        Subject subject = subjectService.findSubjectById(id);
        subjectService.delete(subject);
        System.out.println("дисципліну видалено");
        return "redirect:/admin_teacher_page";

    }
}
