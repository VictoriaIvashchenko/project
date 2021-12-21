package com.assessing.project.additional;

public class InfoForReport {
    private Integer number;
    private String student;
    private Integer course;
    private String speciality;
    private String faculty;
    private String group;
    private Double averageMark;
    private Integer mark;


    public InfoForReport(Integer number, String[] studentName, Double averageMark) {
        this.number = number;
        String studentNameString = "";
        for (String s: studentName) {
            studentNameString += s;
            studentNameString += " ";
        }
        this.student = studentNameString;
        this.averageMark = averageMark;

    }



    public InfoForReport(Integer number, String[] studentName, String group, Double averageMark) {
        this.number = number;
        String studentNameString = "";
        for (String s: studentName) {
            studentNameString += s;
            studentNameString += " ";
        }
        this.student = studentNameString;

        this.group = group;
        this.averageMark = averageMark;

    }
    public InfoForReport(Integer number, String[] studentName, Integer mark) {
        this.number = number;
        String studentNameString = "";
        for (String s: studentName) {
            studentNameString += s;
            studentNameString += " ";
        }
        this.student = studentNameString;

        this.mark = mark;

    }
    public Integer getCourse() {
        return course;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getFaculty() {
        return faculty;
    }

    public InfoForReport(Integer number, Integer cousre, String speciality, String[] studentName, String group, Double averageMark) {
        this.number = number;
        String studentNameString = "";
        for (String s: studentName) {
            studentNameString += s;
            studentNameString += " ";
        }
        this.student = studentNameString;
        this.course = cousre;
        this.speciality = speciality;
        this.group = group;
        this.averageMark = averageMark;

    }
    public InfoForReport(Integer number, String faculty, Integer course, String[] studentName, String group, Double averageMark) {
        this.number = number;
        String studentNameString = "";
        for (String s: studentName) {
            studentNameString += s;
            studentNameString += " ";
        }
        this.student = studentNameString;
        this.course = course;
        this.faculty = faculty;
        this.group = group;
        this.averageMark = averageMark;

    }
    public InfoForReport(Integer number, String faculty, String speciality, String[] studentName, String group, Double averageMark) {
        this.number = number;
        String studentNameString = "";
        for (String s: studentName) {
            studentNameString += s;
            studentNameString += " ";
        }
        this.student = studentNameString;
        this.speciality = speciality;
        this.faculty = faculty;
        this.group = group;
        this.averageMark = averageMark;

    }
    public Integer getNumber() {
        return number;
    }

    public Integer getMark() {
        return mark;
    }

    public String getStudentName() {
        return student;
    }

    public String getGroup() {
        return group;
    }

    public String getAverageMark() {
        return String.format(averageMark.toString(), ".2f");
    }
    public Double getAverageMarkNumber() {
        return averageMark;
    }
}
