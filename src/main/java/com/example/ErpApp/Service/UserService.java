package com.example.ErpApp.Service;

import com.example.ErpApp.Model.UserModel;
import com.example.ErpApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        UserModel user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with username: " + email);
//        }
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//                new ArrayList<>());
//
//    }

    public UserModel findByEmail(String email)
    {
        UserModel user= userRepository.findByEmail(email);
        return user;
    }

    public void saveUser(UserModel user) {

        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encodedPassword= passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
    public Optional<UserModel> findById(long id)
    {
       return userRepository.findById(id);
    }
}
