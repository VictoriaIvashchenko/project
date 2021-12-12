package com.assessing.project.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject_speciality")
public class SubjectSpeciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
    @Column
    private String test_type;

    @OneToMany (mappedBy = "subject_speciality")
    private Set<Mark> marks;

    public SubjectSpeciality() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getTest_type() {
        return test_type;
    }

    public void setTest_type(String test_type) {
        this.test_type = test_type;
    }
}
