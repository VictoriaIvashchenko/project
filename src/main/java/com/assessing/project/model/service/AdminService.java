package com.assessing.project.model.service;

import com.assessing.project.model.entity.Admin;
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
    public Admin findById(Long id) {
        return adminRepository.getById(id);
    }
    public Admin findByLogin(String login){
        return adminRepository.findAdminByLogin(login);
    }
}
