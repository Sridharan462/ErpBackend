package com.example.ErpApp.Controller;

import com.example.ErpApp.Model.*;
import com.example.ErpApp.Repository.FacultyRepository;
import com.example.ErpApp.Repository.StudentRepository;
import com.example.ErpApp.Repository.UserRepository;
import com.example.ErpApp.Security.JwtTokenUtil;
import com.example.ErpApp.Security.MyUserDetailsService;
import com.example.ErpApp.Service.FacultyService;
import com.example.ErpApp.Service.StudentService;
import com.example.ErpApp.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;
    public UserModel loginUser;
    public FacultyDetails facultyDetails;
    private StudentDetails studentDetails;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/Register")
    public String addUser(@RequestBody @Valid UserModel user) throws UsernameNotFoundException {
        if (userService.findByEmail(user.getEmail()) == null) {
            userService.saveUser(user);
            return "new user";
        } else
            throw new UsernameNotFoundException("User already found in database" + user);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        loginUser = userService.findByEmail(authenticationRequest.getEmail());
        if (userService.findByEmail(authenticationRequest.getEmail()) != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean passwordexist = passwordEncoder.matches(authenticationRequest.getPassword(), loginUser.getPassword());
            if (passwordexist) {
                if (loginUser.getUsertype().equals("Staff")) {
                    facultyDetails = facultyService.findByEmail(loginUser.getEmail());
                    if (loginUser.getEmail().equals(facultyDetails.getEmail())) {
                        facultyDetails.setUserId(loginUser.getId());
                        facultyRepository.save(facultyDetails);
                        logger.info("Staff id saved");
                    }
                } else if (loginUser.getUsertype().equals("Student")) {
                    studentDetails = studentService.findByEmail(loginUser.getEmail());
                    if (loginUser.getEmail().equals(studentDetails.getEmail())) {
                        logger.info("Student id saved");
                        studentDetails.setUserId(loginUser.getId());
                        studentRepository.save(studentDetails);
                    }
                }
                final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
                final String jwt = jwtTokenUtil.generateToken(userDetails);
                return ResponseEntity.ok(new AuthenticationResponse(jwt, userService.findByEmail(authenticationRequest.getEmail())));
            } else {
                return ResponseEntity.ok("Incorrect Password");
            }
        } else {
            logger.error("User not present");
            return null;
        }
    }


    @GetMapping("/allUsers")
    public List<UserModel> getAllUsers() {

        return userRepository.findAll();
    }

    @GetMapping("/getAdmin/{id}")
    public Optional<UserModel> getAdmin(@PathVariable Long id) {
        return userService.findById(id);
    }
}



