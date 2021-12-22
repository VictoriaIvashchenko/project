package com.assessing.project.model.service;

import com.assessing.project.model.entity.Admin;
import com.assessing.project.model.entity.Teacher;
import com.assessing.project.model.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private AdminRepository adminRepository;
    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public Admin findById(Integer id) {
        return adminRepository.getById(id);
    }
    public Admin findByLogin(String login){
        return adminRepository.findAdminByLogin(login);
    }
    public String findAdminPasswordByLogin(String login){return adminRepository.findAdminPasswordByLogin(login);}
    public void create(String name, String surname, String patronymic, String login, String password){
        Admin admin = new Admin(name, surname, patronymic, login, password);
        adminRepository.save(admin);
    }
    public String findAdminFullName(Admin admin){
        String[] value = new String[3];
        value[0] = admin.getSurname();
        value[1] = admin.getName();
        value[2] = admin.getPatronymic();
        String adminFullName = "";
        for (String s: value) {
            adminFullName += s;
            adminFullName += " ";
        }
        return adminFullName;
    }
    public String findAdminLogin(Admin admin){
        return admin.getLogin();
    }
}
