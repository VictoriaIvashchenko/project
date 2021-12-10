package com.assessing.project.model.entity;

import javax.persistence.*;

@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer faculty_id;
    @Column
    private Integer speciality_id;

    public Group() {
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

    public Integer getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(Integer faculty_id) {
        this.faculty_id = faculty_id;
    }

    public Integer getSpeciality_id() {
        return speciality_id;
    }

    public void setSpeciality_id(Integer speciality_id) {
        this.speciality_id = speciality_id;
    }
}
