package com.assessing.project.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "`group`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @OneToMany (mappedBy = "group")
    private Set<Student> students;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "subject_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects;

    //    @OneToMany (mappedBy = "group")
//    private Set<SubjectSpeciality> subjectSpecialities;
    public Group() {
    }
    public Group(String name, Faculty faculty, Speciality speciality){
        this.name = name;
        this.faculty = faculty;
        this.speciality = speciality;
    }
    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
    public void addSubjects(Subject subject) {
        this.subjects.add(subject);
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}
