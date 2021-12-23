package com.assessing.project.model.service;

import com.assessing.project.model.entity.*;
import com.assessing.project.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student findStudentByLogin(String login){return studentRepository.findStudentByLogin(login);}
    public String findStudentPasswordByLogin(String login){return studentRepository.findStudentPasswordByLogin(login);}
    public Student findById(Integer id){return studentRepository.getById(id);}
    public List<Student> findAllStudents(){return studentRepository.findAll();}
    public ArrayList<Student> findByGroup(Group group){return studentRepository.findStudentsByGroup(group);}
    public ArrayList<Student> findByCourse(Integer course){return studentRepository.findStudentsByCourse(course);}
    public ArrayList<Student> findBySpeciality(Speciality speciality){
        return studentRepository.findStudentsBySpeciality(speciality);
    }
    public Student findStudentBySurnameAndName(String surname, String name){
        return studentRepository.findStudentBySurnameAndName(surname, name);
    }
    public Integer getIdByStudent(Student student){
        return student.getId();
    }
    public ArrayList<Student> findByFaculty(Faculty faculty){return studentRepository.findStudentsByFaculty(faculty);}
    public String[]findStudentName(Student student){
        String[] value = new String[3];
        value[0] = student.getSurname();
        value[1] = student.getName();
        value[2] = student.getPatronymic();
        return value;
    }
    public String findStudentLogin(Student student){
        return student.getLogin();
    }

    public ArrayList<Student> findStudentsByGroupAndHeightMark(Group group, Integer semester){
        return studentRepository.findStudentsByGroupAndHeightMark(group, semester);
    }
    public ArrayList<Student> findStudentsByGroupAndLowestMark(Group group, Integer semester){
        return studentRepository.findStudentsByGroupAndLowestMark(group, semester);
    }

    public ArrayList<Student> findStudentsByFacultyAndHeightMark(Faculty faculty, Integer semester){
        return studentRepository.findStudentsByFacultyAndHeightMark(faculty, semester);
    }
    public ArrayList<Student> findStudentsByFacultyAndLowestMark(Faculty faculty, Integer semester){
        return studentRepository.findStudentsByFacultyAndLowestMark(faculty, semester);
    }

    public ArrayList<Student> findStudentsBySpecialityAndHeightMark(Speciality speciality, Integer semester){
        return studentRepository.findStudentsBySpecialityAndHeightMark(speciality, semester);
    }
    public ArrayList<Student> findStudentsBySpecialityAndLowestMark(Speciality speciality, Integer semester){
        return studentRepository.findStudentsBySpecialityAndLowestMark(speciality, semester);
    }
    public ArrayList<Student> findStudentsBySemesterAndHeightMark(Integer semester){
        return studentRepository.findStudentsBySemesterAndHeightMark(semester);
    }
    public ArrayList<Student> findStudentsBySemesterAndLowestMark(Integer semester){
        return studentRepository.findStudentsBySemesterAndLowestMark(semester);
    }
    public ArrayList<Student> findStudentsByGroupAndTeacherAndHeightMark(Group group, Teacher teacher){
        return studentRepository.findStudentsByGroupAndTeacherAndHeightMark(group, teacher);
    }
    public ArrayList<Student> findStudentsByGroupAndTeacherAndLowestMark(Group group, Teacher teacher){
        return studentRepository.findStudentsByGroupAndTeacherAndLowestMark(group, teacher);
    }
    public ArrayList<Student> findStudentsBySubjectAndHeightMark(Subject subject){
        return studentRepository.findStudentsBySubjectAndHeightMark(subject);
    }
    public ArrayList<Student> findStudentsBySubjectAndLowestMark(Subject subject){
        return studentRepository.findStudentsBySubjectAndLowestMark(subject);
    }
    public ArrayList<Student> findStudentsBySubjectAndSemesterAndHeightMark(Subject subject, Integer semester){
        return studentRepository.findStudentsBySubjectAndSemesterAndHeightMark(subject, semester);
    }
    public  ArrayList<Student> findStudentsBySubjectAndSemesterAndLowestMark(Subject subject, Integer semester){
        return studentRepository.findStudentsBySubjectAndSemesterAndLowestMark(subject, semester);
    }
    public ArrayList<Student> findStudentsByGroupAndTeacherAndSemesterAndHeightMark(Group group, Teacher teacher,
                                                                             Integer semester){
        return studentRepository.findStudentsByGroupAndTeacherAndSemesterAndHeightMark(group, teacher, semester);
    }
    public ArrayList<Student> findStudentsByGroupAndTeacherAndSemesterAndLowestMark(Group group, Teacher teacher,
                                                                                    Integer semester){
        return studentRepository.findStudentsByGroupAndTeacherAndSemesterAndLowestMark(group, teacher, semester);
    }
    public Integer findCourse(Student student){
        return student.getCourse();
    }

    public void create(String surname, String name, String patronymic,
                       Faculty faculty, Speciality speciality, Group group, Integer course,
                       String login, String password){
        Student student = new Student(surname, name, patronymic, faculty, speciality, group, course, login, password);
        studentRepository.save(student);
    }
    public void update(Student student){
        studentRepository.save(student);
    }
    public void delete(Student student){
        studentRepository.delete(student);
    }

}
