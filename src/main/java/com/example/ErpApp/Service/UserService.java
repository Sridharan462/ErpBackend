package com.example.ErpApp.Service;

import com.example.ErpApp.Model.UserModel;
import com.example.ErpApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public Boolean existsByEmail(String email) {
        Boolean user = userRepository.existsByEmail(email);
        return user;
    }
    public UserModel findByEmail(String email) {
        UserModel user = userRepository.findByEmail(email);
        return user;
    }

    public String saveUser(UserModel user) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "Saved in Database";
    }

    public Optional<UserModel> findById(long id) {
        return userRepository.findById(id);
    }

    public List<UserModel> findAllUsers() {
        return userRepository.findAll();
    }
}
