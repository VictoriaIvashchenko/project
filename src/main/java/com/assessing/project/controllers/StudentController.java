package com.assessing.project.controllers;

import com.assessing.project.model.entity.Mark;
import com.assessing.project.model.entity.Student;

import com.assessing.project.model.service.MarkService;
import com.assessing.project.model.service.StudentService;
import com.assessing.project.model.service.SubjectService;
import com.assessing.project.model.service.TeacherService;
import com.assessing.project.additional.InfoForStudentPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class StudentController {

    Integer semester = 1;
    @Autowired
    StudentService studentService;
    @Autowired
    MarkService markService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    SubjectService subjectService;

    @GetMapping("/student")
    public String student(Model model){
        model.addAttribute("title", "Інф. студента");
//        Student student = studentService.findById(8);
//        ArrayList<Mark> marks = markService.findByStudentAndSemester(student, semester);
//        ArrayList<String[]> teachers = new ArrayList<>();
//        ArrayList<String> subjects = new ArrayList<>();
//        ArrayList<String> typeControl = new ArrayList<>();
//        ArrayList<CurrentStudent> rows = new ArrayList<>();
//        int i = 0;
//        for (Mark mark: marks) {
//            teachers.add(teacherService.findTeacherNameByMark(mark));
//            subjects.add(subjectService.findSubjectNameByMark(mark));
//            typeControl.add(subjectService.findTestTypeByMark(mark));
//            CurrentStudent row = new CurrentStudent(i+1,teachers.get(i), subjects.get(i), typeControl.get(i), markService.findMarkValue(mark));
//            rows.add(row);
//            i++;
//        }
//        Double average = markService.findAverageMark(student, semester);
//        model.addAttribute("marksTable", rows);
//        model.addAttribute("average", average);
        return "student";
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String chooseSemester(@RequestParam("semester") Integer semester, Model model){
        if (semester.intValue() == 0){
            model.addAttribute("table", "nothing");
        }else {
            this.semester = semester;
            Student student = studentService.findById(8);
            ArrayList<Mark> marks = markService.findByStudentAndSemester(student, semester);
            ArrayList<InfoForStudentPage> rows = new ArrayList<>();
            int i = 0;
            for (Mark mark: marks) {

                InfoForStudentPage row = new InfoForStudentPage(i+1,teacherService.findTeacherNameByMark(mark),
                        subjectService.findSubjectNameByMark(mark), subjectService.findTestTypeByMark(mark), markService.findMarkValue(mark));
                rows.add(row);
                i++;
            }
            Double average = markService.findAverageMark(student, semester);
            model.addAttribute("marksTable", rows);
            model.addAttribute("average", average);
            model.addAttribute("table", "something");
        }

        return "student";

    }

}
