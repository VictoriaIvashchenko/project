package com.assessing.project.additional;

public class InfoForStudentPage {
    public Integer number;
    public String teacherName;
    public String subjectName;
    public String test;
    public Integer markValue;
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
