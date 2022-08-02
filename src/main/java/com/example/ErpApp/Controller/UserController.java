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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    public UserModel loginUser;
    public FacultyDetails facultyDetails;
    Logger logger = LoggerFactory.getLogger(UserController.class);
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
    private StudentDetails studentDetails;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    public UserController() {
        super();
    }

    public UserController(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @PostMapping("/Register")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserModel user)  {

        if (!userService.existsByEmail(user.getEmail()))  {
            userService.saveUser(user);
            return ResponseEntity.ok("new user");
        } else
            return ResponseEntity.ok("User already Exists");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws RuntimeException {
        loginUser = userService.findByEmail(authenticationRequest.getEmail());
            if (userService.existsByEmail(authenticationRequest.getEmail())) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                boolean passwordexist = passwordEncoder.matches(authenticationRequest.getPassword(), loginUser.getPassword());
                if (passwordexist) {
                    if (loginUser.getUsertype().equals("Staff")) {
                        facultyDetails = facultyService.findByEmail(loginUser.getEmail());
//                        String email= loginUser.getEmail();
//                        String facultyEmail=facultyDetails.getEmail();
                       try {
                            if (loginUser.getEmail().equals(facultyDetails.getEmail())) {
                                facultyDetails.setUserId(loginUser.getId());
                                facultyRepository.save(facultyDetails);
                                logger.info("Staff id saved");
                            }
                        }
                           catch (Exception e) {
                                logger.error("Your register number is not in database");
                                throw new NoSuchElementException(" Element " + e );
                            }
                    } else if (loginUser.getUsertype().equals("Student")) {
                        studentDetails = studentService.findByEmail(loginUser.getEmail());
                        try {
                            if (loginUser.getEmail().equals(studentDetails.getEmail())) {
                                logger.info("Student id saved");
                                studentDetails.setUserId(loginUser.getId());
                                studentRepository.save(studentDetails);
                            }
                        }
                        catch (Exception e)
                        {
                            logger.error("Your register id is not present in database");
                            throw new NoSuchElementException(" Element " + e);
                        }
                    }
                    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
                    final String jwt = jwtTokenUtil.generateToken(userDetails);
                    return ResponseEntity.ok(new AuthenticationResponse(jwt, userService.findByEmail(authenticationRequest.getEmail())));
                } else {
                    return ResponseEntity.ok("Incorrect Password");
                }
            }
            else {
                throw new UsernameNotFoundException("UserNot Found Exception " + authenticationRequest.getEmail());
            }
    }


    @GetMapping("/allUsers")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/getAdmin/{id}")
    public Optional<UserModel> getAdmin(@PathVariable Long id) throws RuntimeException {

        return userService.findById(id);
    }
}



