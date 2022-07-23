package com.example.ErpApp.Model;


public class AuthenticationResponse {
    private final String jwt;
    private UserModel user;

    public AuthenticationResponse(String jwt, UserModel user) {
        this.jwt = jwt;
        this.user = user;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;

    }
}
