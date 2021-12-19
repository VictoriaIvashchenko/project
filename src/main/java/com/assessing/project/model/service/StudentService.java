package com.assessing.project.model.service;

import com.assessing.project.model.entity.*;
import com.assessing.project.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Student findById(Integer id){return studentRepository.getById(id);}
    public List<Student> findAllStudents(){return studentRepository.findAll();}
    public ArrayList<Student> findByGroup(Group group){return studentRepository.findStudentsByGroup(group);}
    public ArrayList<Student> findByCourse(Integer course){return studentRepository.findStudentsByCourse(course);}
    public ArrayList<Student> findBySpeciality(Speciality speciality){return studentRepository.findStudentsBySpeciality(speciality);}
    public ArrayList<Student> findByFaculty(Faculty faculty){return studentRepository.findStudentsByFaculty(faculty);}
    public String[]findStudentName(Student student){
        String[] value = new String[3];
        value[0] = student.getName();
        value[1] = student.getPatronymic();
        value[2] = student.getSurname();
        return value;
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
    public Integer findCourse(Student student){
        return student.getCourse();
    }

    public void create(String surname, String name, String patronymic,
                       Faculty faculty, Speciality speciality, Group group, Integer course,
                       String login, String password){
        Student student = new Student(surname, name, patronymic, faculty, speciality, group, course, login, password);
        studentRepository.save(student);
    }

}
