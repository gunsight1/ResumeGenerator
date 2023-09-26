package com.resumegenerator.VO;

/**
* @className    : PersonInfo
* @fileName     : PersonInfo.java
* @author       : Jiyong Jung
* @date         : 2023-09-26 :: 오후 4:00
* @desc         : 개인정보 VO
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023-09-26 :: 오후 4:00        Jiyong Jung       최초 생성
*/public class PersonInfo{
    private String name;
    private String email;
    private String addr;
    private String phone;
    private String birth;

    private String photoKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhotoKey() {
        return photoKey;
    }

    public void setPhotoKey(String photoKey) {
        this.photoKey = photoKey;
    }
}
