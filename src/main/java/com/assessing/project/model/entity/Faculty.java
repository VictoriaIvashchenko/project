package com.assessing.project.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;

    @OneToMany (mappedBy = "faculty")
    private Set<Group> groups;

    @OneToMany (mappedBy = "faculty")
    private Set<Student> students;

    public Faculty() {
    }
    public Faculty(String name){
        this.name = name;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
