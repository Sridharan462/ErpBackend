package com.example.ErpApp.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter
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

}
