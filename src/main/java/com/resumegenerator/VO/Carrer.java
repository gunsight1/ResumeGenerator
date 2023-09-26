package com.resumegenerator.VO;

/**
* @className    : Carrer
* @fileName     : Carrer.java
* @author       : Jiyong Jung
* @date         : 2023-09-26 :: 오후 3:53
* @desc         : 경력 정보 VO
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023-09-26 :: 오후 3:53        Jiyong Jung       최초 생성
*/public class Carrer {
    private String workPeriod;  //근무기간
    private String companyName; //기업 명
    private String jobTitle;    //직책
    private String employYears; //근속년수

    public String getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(String workPeriod) {
        this.workPeriod = workPeriod;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmployYears() {
        return employYears;
    }

    public void setEmployYears(String employYears) {
        this.employYears = employYears;
    }
}
