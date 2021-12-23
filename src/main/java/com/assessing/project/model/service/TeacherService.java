package com.assessing.project.model.service;

import com.assessing.project.model.entity.Admin;
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
    public Teacher findTeacherByLogin(String login){return teacherRepository.findTeacherByLogin(login);}
    public String findTeacherPasswordByLogin(String login){return teacherRepository.findTeacherPasswordByLogin(login);}
    public String[]findTeacherNameByMark(Mark mark){
        Teacher teacher = teacherRepository.customFindTeacherByMark(mark);
        String[] value = new String[3];
        value[0] = teacher.getSurname();
        value[1] = teacher.getName();
        value[2] = teacher.getPatronymic();
        return value;
    }
    public String findTeacherFullName(Teacher teacher){
        String[] value = new String[3];
        value[0] = teacher.getSurname();
        value[1] = teacher.getName();
        value[2] = teacher.getPatronymic();
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
    public Teacher findTeacherBySurnameAndName(String surname, String name){
        return teacherRepository.findTeacherBySurnameAndName(surname, name);
    }
    public Teacher findTeacherBySurname(String surname){return teacherRepository.findTeacherBySurname(surname);}
    public Teacher findTeacherById(Integer id){return teacherRepository.findTeacherById(id);}
    public Integer findIdByTeacher(Teacher teacher){ return teacher.getId();}
    public void create(String surname, String name, String patronymic, String login, String password){
        Teacher teacher = new Teacher(surname, name, patronymic, login, password);
        teacherRepository.save(teacher);
    }
    public void update(Teacher teacher){
        teacherRepository.save(teacher);
    }
    public void delete(Teacher teacher){
        teacherRepository.delete(teacher);
    }
}
