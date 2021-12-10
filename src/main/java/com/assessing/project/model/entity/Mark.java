package com.assessing.project.model.entity;

import javax.persistence.*;

@Entity
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer student_id;
    @Column
    private Integer subject_sp_id;
    @Column
    private Double value;

    public Mark() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getSubject_sp_id() {
        return subject_sp_id;
    }

    public void setSubject_sp_id(Integer subject_sp_id) {
        this.subject_sp_id = subject_sp_id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
