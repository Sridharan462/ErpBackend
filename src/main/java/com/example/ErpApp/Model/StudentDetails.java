package com.example.ErpApp.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Student")
public class StudentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String roll;
    private String course;
    private String reg;
    private String gender;
    private Date dob;
    private String nationality;
    private String community;
    private String religion;
    private String caste;
    private String lang;
    private String blood;
    private String address;
    private String fname;
    private String mname;
    private String info;
    private String info1;
    private String fmail;
    private String email;
    private String fnum;
    private String mnum;
    private String number;
    private String aadharnumber;
    private long userId;

    public StudentDetails() {
    }

    public StudentDetails(long id, String name, String roll, String course, String reg, String gender, Date dob, String nationality, String community, String religion, String caste, String lang, String blood, String address, String fname, String mname, String info, String info1, String fmail, String email, String fnum, String mnum, String number, String aadharnumber, long userId) {
        this.id = id;
        this.name = name;
        this.roll = roll;
        this.course = course;
        this.reg = reg;
        this.gender = gender;
        this.dob = dob;
        this.nationality = nationality;
        this.community = community;
        this.religion = religion;
        this.caste = caste;
        this.lang = lang;
        this.blood = blood;
        this.address = address;
        this.fname = fname;
        this.mname = mname;
        this.info = info;
        this.info1 = info1;
        this.fmail = fmail;
        this.email = email;
        this.fnum = fnum;
        this.mnum = mnum;
        this.number = number;
        this.aadharnumber = aadharnumber;
        this.userId = userId;
    }

    @Builder(builderMethodName = "builder")
    public static StudentDetails newStudent(long id, String name, String roll, String course, String reg, String gender, Date dob, String nationality, String community, String religion, String caste, String lang, String blood, String address, String fname, String mname, String info, String info1, String fmail, String email, String fnum, String mnum, String number, String aadharnumber, long userId) {
     return new StudentDetails(id,name,roll,course,reg,gender,dob,nationality,community,religion,caste,lang,blood,address, fname,mname,  info,info1,fmail,email,fnum,mnum,number,aadharnumber,userId);
    }

}
