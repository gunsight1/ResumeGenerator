package com.resumegenerator.VO;

import java.util.ArrayList;

/**
* @className    : Education
* @fileName     : Education.java
* @author       : Jiyong Jung
* @date         : 2023-09-26 :: 오후 3:57
* @desc         : 학력정보 VO
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023-09-26 :: 오후 3:57        Jiyong Jung       최초 생성
*/public class Education {
    private String graduationYear; //졸업년도
    private String schoolName;  //학교 명
    private String major;       //전공
    private String graduationStatus; //졸업여부

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGraduationStatus() {
        return graduationStatus;
    }

    public void setGraduationStatus(String graduationStatus) {
        this.graduationStatus = graduationStatus;
    }
}
