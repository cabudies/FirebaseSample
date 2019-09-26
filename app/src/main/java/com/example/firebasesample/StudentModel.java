package com.example.firebasesample;

public class StudentModel {

    public String studentName, studentAddress;
    public long studentPhone;

    public StudentModel(String studentName, String studentAddress, long studentPhone) {
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.studentPhone = studentPhone;
    }

    public String getStudentName() {
        return studentName;
    }

    public long getStudentPhone() {
        return studentPhone;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentPhone(long studentPhone) {
        this.studentPhone = studentPhone;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }
}
