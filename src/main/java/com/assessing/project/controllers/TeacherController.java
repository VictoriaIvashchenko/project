package com.assessing.project.controllers;

import com.assessing.project.additional.GettingWrapper;
import com.assessing.project.additional.InfoForReport;
import com.assessing.project.additional.InfoForTeacherPage;
import com.assessing.project.config.SecurityUser;
import com.assessing.project.model.entity.*;
import com.assessing.project.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    GroupService groupService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    StudentService studentService;
    @Autowired
    MarkService markService;
    @Autowired
    FacultyService facultyService;

    Teacher teacher = new Teacher();

    @GetMapping("/teacher")
    public String teacher(Model model){
        model.addAttribute("title", "Інф. викладача");

        //видобуток поточного вчителя після авторизації
        SecurityUser curr = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Teacher currentTeacher = teacherService.findTeacherByLogin(curr.getUsername());
        teacher = currentTeacher;
        ArrayList<Group> groups = groupService.findGroupsByTeacher(teacher);
        ArrayList<String> groupNames = new ArrayList<>();
        for (Group group: groups) {
            groupNames.add(groupService.findGroupName(group));
        }
        String teacherName = teacherService.findTeacherFullName(teacher);
        model.addAttribute("teacherName", teacherName);
        model.addAttribute("groups", groupNames);

        return "teacher";
    }
    @GetMapping("/teacher_report")
    public String teacherReport(Model model){
        model.addAttribute("title", "Інф. викладача");
        System.out.println(teacherService.findTeacherFullName(teacher));
        String teacherName = teacherService.findTeacherFullName(teacher);
        model.addAttribute("teacherName", teacherName);
        return "teacher_report";
    }
    @GetMapping("/teacher_report_subject")
    public String teacherReportSubject(Model model){
        model.addAttribute("title", "Звіти викладача по предмету");
        ArrayList<Subject> subjects = subjectService.findSubjectsByTeacher(teacher);
        ArrayList<String> subjectNames = new ArrayList<>();
        for (Subject subject: subjects) {
            subjectNames.add(subjectService.findSubjectName(subject));
        }
        String teacherName = teacherService.findTeacherFullName(teacher);
        model.addAttribute("teacherName", teacherName);
        model.addAttribute("subjects", subjectNames);
        return "teacher_report_subject";
    }
    @GetMapping("/teacher_report_group")
    public String teacherReportGroup(Model model){
        String teacherName = teacherService.findTeacherFullName(teacher);
        model.addAttribute("teacherName", teacherName);
        model.addAttribute("title", "Звіти викладача по группі");
        ArrayList<Group> groups = groupService.findGroupsByTeacher(teacher);
        ArrayList<String> groupNames = new ArrayList<>();
        for (Group group: groups) {
            groupNames.add(groupService.findGroupName(group));
        }
        model.addAttribute("groups", groupNames);
        return "teacher_report_group";
    }

    @PostMapping("/teacher")
    public String getGroup(@RequestParam("groupName") String groupName, @RequestParam("semester") Integer semester, Model model){
        String teacherName = teacherService.findTeacherFullName(teacher);
        model.addAttribute("teacherName", teacherName);
        ArrayList<Group> groups = groupService.findGroupsByTeacher(teacher);
        ArrayList<String> groupNames = new ArrayList<>();
        for (Group group: groups) {
            groupNames.add(groupService.findGroupName(group));
        }
        model.addAttribute("groups", groupNames);
        if (groupName.equals("--")|| semester == 0){
            model.addAttribute("table", "nothing");
        }else {
            Group group = groupService.findGroupByName(groupName);
            String faculty = facultyService.findFacultyName(facultyService.findFacultyByGroup(group));
            Subject subject = subjectService.findSubjectNameByGroupAndTeacher(group, teacher);
            ArrayList<Student> students = studentService.findByGroup(group);

            String testType = subjectService.findTestTypeBySubject(subjectService.findSubjectByName(subjectService.findSubjectName(subject)));
            ArrayList<InfoForTeacherPage> rows = new ArrayList<>();

            for (int i = 0; i < students.size(); i++) {
                InfoForTeacherPage row = new InfoForTeacherPage(i + 1,
                        studentService.findStudentName(students.get(i)),
                        markService.markGetIntegerValue(markService.findMarkByStudentAndSubjectAndSemester(students.get(i),
                                subjectService.findSubjectByName(subjectService.findSubjectName(subject)),semester)));
                rows.add(row);
            }
            model.addAttribute("group", groupName);
            model.addAttribute("testType", testType);
            model.addAttribute("faculty", faculty);
            model.addAttribute("subject", subjectService.findSubjectName(subject));
            model.addAttribute("marksTable", rows);
            model.addAttribute("table", "something");
            model.addAttribute("groupNameBtn", groupName);
            model.addAttribute("semester", semester);
        }
        return "teacher";
    }
    @PostMapping("/teacher_report_subject")
    public String teacherReportSubjectPost(@RequestParam("subjectName") String subjectName, @RequestParam("semester") Integer semester, Model model){
        String teacherName = teacherService.findTeacherFullName(teacher);
        model.addAttribute("teacherName", teacherName);
        ArrayList<Subject> subjects = subjectService.findSubjectsByTeacher(teacher);
        ArrayList<String> subjectNames = new ArrayList<>();
        for (Subject subject: subjects) {
            subjectNames.add(subjectService.findSubjectName(subject));
        }
        model.addAttribute("subjects", subjectNames);
        if (subjectName.equals("--")) {
            model.addAttribute("tables", "nothing");
        }
        else {
            model.addAttribute("tables", "something");

            ArrayList<Student> studentsHeight = studentService.findStudentsBySubjectAndSemesterAndHeightMark(subjectService.findSubjectByName(subjectName), semester);


            ArrayList<Student> studentsLow = studentService.findStudentsBySubjectAndSemesterAndLowestMark(subjectService.findSubjectByName(subjectName), semester);

            if (studentsHeight.size()==0){
                model.addAttribute("tableHeight", "nothing");
                System.out.println("nothing");
            }
            else {

                ArrayList<InfoForReport> infoStudentsHeight = new ArrayList<>();
                int i = 1;
                for (Student student: studentsHeight) {
                    InfoForReport infoStudentHeight = new InfoForReport(i, studentService.findStudentName(student),
                            groupService.findGroupByStudent(student),
                            markService.markGetIntegerValue(markService.findMarkByStudentAndSubjectAndSemester(student,
                                    subjectService.findSubjectByName(subjectName), semester)));

                    if (infoStudentHeight.getMark() != 0){
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
                System.out.println("nothing");
            }
            else {
                model.addAttribute("tableLow", "something");
                ArrayList<InfoForReport> infoStudentsLow = new ArrayList<>();

                int i = 1;
                for (Student student: studentsLow) {
                    InfoForReport infoStudentLow = new InfoForReport(i, studentService.findStudentName(student),
                            groupService.findGroupByStudent(student), markService.markGetIntegerValue(markService.findMarkByStudentAndSubjectAndSemester(student, subjectService.findSubjectByName(subjectName), semester)));
                    infoStudentsLow.add(infoStudentLow);
                    i++;
                }
                model.addAttribute("studentsLow", infoStudentsLow);
            }


        }
        return "teacher_report_subject";
    }
    @PostMapping("/teacher_report_group")
    public String teacherReportGroupPost(@RequestParam("groupName") String groupName, @RequestParam("semester") Integer semester, Model model){
        String teacherName = teacherService.findTeacherFullName(teacher);
        model.addAttribute("teacherName", teacherName);
        ArrayList<Group> groups = groupService.findGroupsByTeacher(teacher);
        ArrayList<String> groupNames = new ArrayList<>();
        for (Group group: groups) {
            groupNames.add(groupService.findGroupName(group));
        }
        model.addAttribute("groups", groupNames);
        if (groupName.equals("--")) {
            model.addAttribute("tables", "nothing");
        }
        else {
            model.addAttribute("tables", "something");
            ArrayList<Student> studentsHeight = studentService.findStudentsByGroupAndTeacherAndSemesterAndHeightMark(groupService.findGroupByName(groupName),teacher, semester);
            ArrayList<Student> studentsLow = studentService.findStudentsByGroupAndTeacherAndSemesterAndLowestMark(groupService.findGroupByName(groupName),teacher, semester);
            if (studentsHeight.size()==0){
                model.addAttribute("tableHeight", "nothing");
            }
            else {

                ArrayList<InfoForReport> infoStudentsHeight = new ArrayList<>();
                int i = 1;
                for (Student student: studentsHeight) {
                    InfoForReport infoStudentHeight = new InfoForReport(i, studentService.findStudentName(student), markService.markGetIntegerValue(markService.findMarkByStudentAndTeacherAndSemester(student, teacher, semester)));
                    if (infoStudentHeight.getMark() != 0){
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
                            markService.markGetIntegerValue(markService.findMarkByStudentAndTeacherAndSemester(student, teacher, semester)));
                    infoStudentsLow.add(infoStudentLow);
                    i++;
                }
                model.addAttribute("studentsLow", infoStudentsLow);
            }

        }
        return "teacher_report_group";
    }
    @GetMapping("/teacher_set_marks/{groupName}/{semester}")
    public String teacherSetMarks(@PathVariable(value = "groupName") String groupName,
                                  @PathVariable(value = "semester") Integer semester, Model model){
        String teacherName = teacherService.findTeacherFullName(teacher);
        model.addAttribute("teacherName", teacherName);
        model.addAttribute("title", "Виставлення оцінок");
        Group group = groupService.findGroupByName(groupName);
        String faculty = facultyService.findFacultyName(facultyService.findFacultyByGroup(group));
        Subject subject = subjectService.findSubjectNameByGroupAndTeacher(group, teacher);
        ArrayList<Student> students = studentService.findByGroup(group);

        String testType = subjectService.findTestTypeBySubject(subjectService.findSubjectByName(subjectService.findSubjectName(subject)));
        ArrayList<InfoForTeacherPage> rows = new ArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            InfoForTeacherPage row = new InfoForTeacherPage(i + 1,
                    studentService.findStudentName(students.get(i)),
                    markService.markGetIntegerValue(markService.findMarkByStudentAndSubjectAndSemester(students.get(i),
                            subjectService.findSubjectByName(subjectService.findSubjectName(subject)),semester)));

            rows.add(row);
        }
        model.addAttribute("groupName", groupName);
//        model.addAttribute("testType", testType);
//        model.addAttribute("faculty", faculty);
//        model.addAttribute("subject", subject);
        model.addAttribute("marksTable", rows);
        GettingWrapper wrapper = new GettingWrapper();
        model.addAttribute("wrapper", wrapper);
        return "teacher_set_marks";
    }
    @PostMapping("/teacher_set_marks/{groupName}/{semester}")
    public String teacherSetMarksPost(@PathVariable(value = "groupName") String groupName, @PathVariable(value = "semester") Integer semester,
                                      Model model, @RequestParam("studentMark") ArrayList<Integer> marks){
        String teacherName = teacherService.findTeacherFullName(teacher);
        model.addAttribute("teacherName", teacherName);
        Group group = groupService.findGroupByName(groupName);
        Subject subject = subjectService.findSubjectNameByGroupAndTeacher(group, teacher);
        ArrayList<Student> students = studentService.findByGroup(group);
        for (int i = 0; i<marks.size();i++) {

            Mark mark = markService.findMarkByStudentAndSubjectAndSemester(students.get(i), subject, semester);
            if ( mark == null){

                markService.create(students.get(i),subject , marks.get(i), semester);
                System.out.println("Оцінку створено");
            }
            else {
                mark.setValue(marks.get(i));
                markService.update(mark);
                System.out.println("Оцінку змінено");
            }
        }
        return "redirect:/teacher";
    }

}
