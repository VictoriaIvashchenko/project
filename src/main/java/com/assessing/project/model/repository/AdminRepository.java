package com.assessing.project.model.repository;

import com.assessing.project.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findAdminByLogin(String login);
}
