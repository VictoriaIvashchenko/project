package com.assessing.project.controllers;

import com.assessing.project.additional.Getting;
import com.assessing.project.additional.GettingWrapper;
import com.assessing.project.additional.InfoForReport;
import com.assessing.project.additional.InfoForTeacherPage;
import com.assessing.project.config.SecurityUser;
import com.assessing.project.model.entity.Group;
import com.assessing.project.model.entity.Student;
import com.assessing.project.model.entity.Subject;
import com.assessing.project.model.entity.Teacher;
import com.assessing.project.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    public String getGroup(@RequestParam("groupName") String groupName, Model model){
        String teacherName = teacherService.findTeacherFullName(teacher);
        model.addAttribute("teacherName", teacherName);
        ArrayList<Group> groups = groupService.findGroupsByTeacher(teacher);
        ArrayList<String> groupNames = new ArrayList<>();
        for (Group group: groups) {
            groupNames.add(groupService.findGroupName(group));
        }
        model.addAttribute("groups", groupNames);
        if (groupName.equals("--")){
            model.addAttribute("table", "nothing");
        }else {
            Group group = groupService.findGroupByName(groupName);
            String faculty = facultyService.findFacultyName(facultyService.findFacultyByGroup(group));
            String subject = subjectService.findSubjectNameByGroupAndTeacher(group, teacher);
            ArrayList<Student> students = studentService.findByGroup(group);

            String testType = subjectService.findTestTypeBySubject(subjectService.findSubjectByName(subject));
            ArrayList<InfoForTeacherPage> rows = new ArrayList<>();

            for (int i = 0; i < students.size(); i++) {
                InfoForTeacherPage row = new InfoForTeacherPage(i + 1,
                        studentService.findStudentName(students.get(i)),
                        markService.findMarkByStudentAndSubject(students.get(i), subjectService.findSubjectByName(subject)));
                rows.add(row);
            }
            model.addAttribute("group", groupName);
            model.addAttribute("testType", testType);
            model.addAttribute("faculty", faculty);
            model.addAttribute("subject", subject);
            model.addAttribute("marksTable", rows);
            model.addAttribute("table", "something");
            model.addAttribute("groupNameBtn", groupName);
        }
        return "teacher";
    }
    @PostMapping("/teacher_report_subject")
    public String teacherReportSubjectPost(@RequestParam("subjectName") String subjectName, Model model){
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
            ArrayList<Group> groups = groupService.findGroupBySubject(subjectService.findSubjectByName(subjectName));
            ArrayList<Student> studentsHeight = new ArrayList<>();
            for (Group group: groups) {
                studentsHeight.addAll(studentService.findStudentsByGroupAndHeightMark(group,1));
            }

            ArrayList<Student> studentsLow = new ArrayList<>();
            for (Group group: groups) {
                studentsLow.addAll(studentService.findStudentsByGroupAndLowestMark(group,1));
            }
            if (studentsHeight.size()==0){
                model.addAttribute("tableHeight", "nothing");
            }
            else {

                ArrayList<InfoForReport> infoStudentsHeight = new ArrayList<>();
                int i = 1;
                for (Student student: studentsHeight) {
                    InfoForReport infoStudentHeight = new InfoForReport(i, studentService.findStudentName(student),
                            groupService.findGroupByStudent(student), markService.findAverageMark(student, 1));

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
                            groupService.findGroupByStudent(student), markService.findAverageMark(student, 1));
                    infoStudentsLow.add(infoStudentLow);
                    i++;
                }
                model.addAttribute("studentsLow", infoStudentsLow);
            }


        }
        return "teacher_report_subject";
    }
    @PostMapping("/teacher_report_group")
    public String teacherReportGroupPost(@RequestParam("groupName") String groupName, Model model){
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
            ArrayList<Student> studentsHeight = studentService.findStudentsByGroupAndHeightMark(groupService.findGroupByName(groupName),1);
            ArrayList<Student> studentsLow = studentService.findStudentsByGroupAndLowestMark(groupService.findGroupByName(groupName),1);
            if (studentsHeight.size()==0){
                model.addAttribute("tableHeight", "nothing");
            }
            else {

                ArrayList<InfoForReport> infoStudentsHeight = new ArrayList<>();
                int i = 1;
                for (Student student: studentsHeight) {
                    InfoForReport infoStudentHeight = new InfoForReport(i, studentService.findStudentName(student), markService.findAverageMark(student, 1));
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
                            markService.findAverageMark(student, 1));
                    infoStudentsLow.add(infoStudentLow);
                    i++;
                }
                model.addAttribute("studentsLow", infoStudentsLow);
            }

        }
        return "teacher_report_group";
    }
    @GetMapping("/teacher_set_marks/{groupName}")
    public String teacherSetMarks(@PathVariable(value = "groupName") String groupName, Model model){
        String teacherName = teacherService.findTeacherFullName(teacher);
        model.addAttribute("teacherName", teacherName);
        model.addAttribute("title", "Виставлення оцінок");
        Group group = groupService.findGroupByName(groupName);
        String faculty = facultyService.findFacultyName(facultyService.findFacultyByGroup(group));
        String subject = subjectService.findSubjectNameByGroupAndTeacher(group, teacher);
        ArrayList<Student> students = studentService.findByGroup(group);

        String testType = subjectService.findTestTypeBySubject(subjectService.findSubjectByName(subject));
        ArrayList<InfoForTeacherPage> rows = new ArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            InfoForTeacherPage row = new InfoForTeacherPage(i + 1,
                    studentService.findStudentName(students.get(i)),
                    markService.findMarkByStudentAndSubject(students.get(i), subjectService.findSubjectByName(subject)));
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
    @PostMapping("/teacher_set_marks/{groupName}")
    public String teacherSetMarksPost(@PathVariable(value = "groupName") String groupName, @ModelAttribute GettingWrapper wrapper, Model model){
        String teacherName = teacherService.findTeacherFullName(teacher);
        model.addAttribute("teacherName", teacherName);

        return "redirect:/teacher";
    }

}
