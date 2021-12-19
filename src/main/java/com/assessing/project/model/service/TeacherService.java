package com.assessing.project.model.service;

import com.assessing.project.model.entity.Mark;
import com.assessing.project.model.entity.Teacher;
import com.assessing.project.model.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private TeacherRepository teacherRepository;
    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    public String[]findTeacherNameByMark(Mark mark){
        Teacher teacher = teacherRepository.customFindTeacherByMark(mark);
        String[] value = new String[3];
        value[0] = teacher.getName();
        value[1] = teacher.getPatronymic();
        value[2] = teacher.getSurname();
        return value;
    }
    public String findTeacherFullName(Teacher teacher){
        String[] value = new String[3];
        value[0] = teacher.getName();
        value[1] = teacher.getPatronymic();
        value[2] = teacher.getSurname();
        String teacherFullName = "";
        for (String s: value) {
            teacherFullName += s;
            teacherFullName += " ";
        }
        return teacherFullName;
    }
    public String findTeacherLogin(Teacher teacher){
        return teacher.getLogin();
    }
    public String findTeacherPassword(Teacher teacher){
        return teacher.getPassword();
    }

    public Teacher findTeacherBySurname(String surname){return teacherRepository.findTeacherBySurname(surname);}
    public Teacher findTeacherById(Integer id){return teacherRepository.findTeacherById(id);}
    public void create(String name, String surname, String patronymic, String login, String password){
        Teacher teacher = new Teacher(name, surname, patronymic, login, password);
        teacherRepository.save(teacher);
    }
}
