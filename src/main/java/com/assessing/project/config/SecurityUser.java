package com.assessing.project.config;

import com.assessing.project.model.entity.Admin;
import com.assessing.project.model.entity.Student;
import com.assessing.project.model.entity.Teacher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SecurityUser  implements UserDetails {

    private final String login;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public SecurityUser(String login, String password, List<SimpleGrantedAuthority> list, boolean isActive) {
        this.login = login;
        this.password = password;
        this.isActive = isActive;
        this.authorities = list;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromAdmin(Admin admin){
        List<SimpleGrantedAuthority> grantList = new ArrayList<SimpleGrantedAuthority>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
        grantList.add(authority);
        return  new SecurityUser(admin.getLogin(), admin.getPassword(), grantList, true);
    }
    public static UserDetails fromStudent(Student student){
        List<SimpleGrantedAuthority> grantList = new ArrayList<SimpleGrantedAuthority>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("STUDENT");
        grantList.add(authority);
        return  new SecurityUser(student.getLogin(), student.getPassword(), grantList, true);
    }
    public static UserDetails fromTeacher(Teacher teacher){
        List<SimpleGrantedAuthority> grantList = new ArrayList<SimpleGrantedAuthority>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("TEACHER");
        grantList.add(authority);
        return  new SecurityUser(teacher.getLogin(), teacher.getPassword(), grantList, true);
    }
}
