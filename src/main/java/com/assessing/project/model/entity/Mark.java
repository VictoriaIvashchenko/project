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
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    @Column
    private Integer value;
    @Column
    private Integer semester;

    public Mark() {
    }
    public Mark(Student student, Subject subject, Integer value, Integer semester){
        this.student = student;
        this.value = value;
        this.subject = subject;
        this.semester = semester;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}
