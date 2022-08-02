package com.example.ErpApp.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter @Getter
@Entity
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "username should not empty")
    private String username;
    @NotNull
    @NotBlank
    private String password;
    private String usertype;
    @Email
    private String email;

    public UserModel() {
    }

    public UserModel(long id,String username, String password, String usertype, String email) {

        this.username = username;
        this.password = password;
        this.usertype = usertype;
        this.email = email;
    }

    @Builder(builderMethodName = "builder")
    public static UserModel newUser(long id,String username, String password, String usertype, String email) {
        return new UserModel(id,username,password,usertype,email);
    }
}
