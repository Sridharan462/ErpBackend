package com.example.ErpApp.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "Faculty")
public class FacultyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String register;
    private Date dob;
    private Date doj;
    private String number;
    private String degree;
    ////    @OneToOne(cascade = CascadeType.ALL)
////    private UserModel user;
////    @OneToOne(cascade = CascadeType.ALL)
////    private Department department;
    private long userId;

}
