package com.assessing.project.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mark {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Long student_id;
    @Column
    private Long subject_sp_id;
    @Column
    private Double value;

    public Mark() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getSubject_sp_id() {
        return subject_sp_id;
    }

    public void setSubject_sp_id(Long subject_sp_id) {
        this.subject_sp_id = subject_sp_id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
