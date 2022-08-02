package com.example.ErpApp.Model;

import junit.runner.Version;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

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
    private long userId;

    public FacultyDetails() {
    }

    public FacultyDetails(long id, String name, String email, String register, Date dob, Date doj, String number, String degree, long userId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.register = register;
        this.dob = dob;
        this.doj = doj;
        this.number = number;
        this.degree = degree;
        this.userId = userId;
    }

    @Builder(builderMethodName = "builder")
    public static FacultyDetails newFaculty(long id,String name, String email, String register, Date dob, Date doj, String number, String degree, long userId) {
        return new FacultyDetails(id, name,email,register,dob,doj,number,degree,userId);
    }


}
