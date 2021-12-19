package com.assessing.project.additional;

public class InfoForTeacherPage {
    private Integer number;
    private String student;
    private Integer mark;

    public InfoForTeacherPage(Integer number, String[] studentName, Integer mark) {
        this.number = number;
        String studentNameString = "";
        for (String s: studentName) {
            studentNameString += s;
            studentNameString += " ";
        }
        this.student = studentNameString;
        this.mark = mark;
    }

    public Integer getNumber() {
        return number;
    }

    public String getStudentName() {
        return student;
    }

    public Integer getMark() {
        return mark;
    }
}
