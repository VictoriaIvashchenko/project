package com.assessing.project.additional;

public class InfoForStudentPage {
    public Integer number;
    public String teacherName;
    public String subjectName;
    public String test;
    public Integer markValue;

    public Integer getNumber() {
        return number;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getTest() {
        return test;
    }

    public Integer getMarkValue() {
        return markValue;
    }


    public InfoForStudentPage(Integer number, String[] teacherName, String subjectName, String test, Integer markValue){
        this.number = number;
        String teacherNameString = "";
        for (String s: teacherName) {
            teacherNameString += s;
            teacherNameString += " ";
        }
        this.teacherName = teacherNameString;
        this.subjectName = subjectName;
        this.test = test;
        this.markValue = markValue;
    }
}
