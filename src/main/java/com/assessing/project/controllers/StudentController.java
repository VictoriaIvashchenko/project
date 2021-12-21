package com.assessing.project.controllers;

import com.assessing.project.config.SecurityUser;
import com.assessing.project.model.entity.Admin;
import com.assessing.project.model.entity.Mark;
import com.assessing.project.model.entity.Student;

import com.assessing.project.model.service.*;
import com.assessing.project.additional.InfoForStudentPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    MarkService markService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    FacultyService facultyService;
    @Autowired
    GroupService groupService;
    Student student = new Student();

    @GetMapping("/student")
    public String student(Model model){
        model.addAttribute("title", "Інф. студента");
        //видобуток поточного студента після авторизації
        SecurityUser curr = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        student = studentService.findStudentByLogin(curr.getUsername());
        String[] studentName = studentService.findStudentName(student);
        model.addAttribute("surname", studentName[0]);
        model.addAttribute("name", studentName[1]);
        model.addAttribute("facultyName", facultyService.findFacultyName(facultyService.findFacultyByStudent(student)));
        model.addAttribute("groupName", groupService.findGroupByStudent(student));
        return "student";
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String chooseSemester(@RequestParam("semester") Integer semester, Model model){
        String[] studentName = studentService.findStudentName(student);
        model.addAttribute("surname", studentName[0]);
        model.addAttribute("name", studentName[1]);
        model.addAttribute("facultyName", facultyService.findFacultyName(facultyService.findFacultyByStudent(student)));
        model.addAttribute("groupName", groupService.findGroupByStudent(student));
        if (semester == 0){
            model.addAttribute("table", "nothing");
        }else {
            List<Mark> marks = markService.findByStudentAndSemester(student, semester);
            if (marks.size() == 0){
                model.addAttribute("table", "nothing");
            }
            else {
                ArrayList<InfoForStudentPage> rows = new ArrayList<>();
                int i = 0;
                for (Mark mark: marks) {
                    System.out.println(subjectService.findSubjectNameByMark(mark));
                    InfoForStudentPage row = new InfoForStudentPage(i+1,teacherService.findTeacherNameByMark(mark),
                            subjectService.findSubjectNameByMark(mark), subjectService.findTestTypeByMark(mark), markService.findMarkValue(mark));
                    rows.add(row);
                    i++;
                }
                Double average = markService.findAverageMark(student, semester);
                model.addAttribute("marksTable", rows);
                model.addAttribute("average", String.format(average.toString(), ".2f"));
                model.addAttribute("table", "something");
            }
        }
        return "student";

    }

}
