package com.assessing.project.config;

import com.assessing.project.model.entity.Admin;
import com.assessing.project.model.entity.Student;
import com.assessing.project.model.entity.Teacher;
import com.assessing.project.model.repository.AdminRepository;
import com.assessing.project.model.repository.StudentRepository;
import com.assessing.project.model.repository.TeacherRepository;
import com.assessing.project.model.service.AdminService;
import com.assessing.project.model.service.StudentService;
import com.assessing.project.model.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService{

    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    private  final AdminService adminService;
    private  final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public UserDetailsServiceImpl(AdminRepository adminRepository, StudentRepository studentRepository,
                                  TeacherRepository teacherRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;

        this.adminService = new AdminService(adminRepository);
        this.studentService = new StudentService(studentRepository);
        this.teacherService = new TeacherService(teacherRepository);

    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Admin adm = adminService.findByLogin(login.trim());
        Student std = studentService.findStudentByLogin(login.trim());
        Teacher teach = teacherService.findTeacherByLogin(login.trim());
        if(std != null) {
            return SecurityUser.fromStudent(std);
        }
        if (adm != null) {
            return SecurityUser.fromAdmin(adm);
        }

        if(teach != null){
            return  SecurityUser.fromTeacher(teach);
        }
        return null;


    }



}
