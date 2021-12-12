package com.assessing.project.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;

    @OneToMany (mappedBy = "subject")
    private Set<SubjectSpeciality> subjectSpecialities;

    @OneToMany (mappedBy = "subject")
    private Set<SubjectTeacher> subjectTeachers;

    public Subject() {
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
