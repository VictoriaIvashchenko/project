package com.assessing.project.model.repository;

import com.assessing.project.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findAdminByLogin(String login);
    @Query("select a.password from Admin as a where a.login = :#{#login}")
    String findAdminPasswordByLogin(@Param("login") String login);

}
