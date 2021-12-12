package com.assessing.project.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "mark")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "subject_speciality_id", nullable = false)
    private SubjectSpeciality subject_speciality;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SubjectSpeciality getSubject_sp() {
        return subject_speciality;
    }

    public void setSubject_sp(SubjectSpeciality subject_speciality) {
        this.subject_speciality = subject_speciality;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
