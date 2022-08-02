package com.example.ErpApp.Controller;

import com.example.ErpApp.Model.AuthenticationRequest;
import com.example.ErpApp.Model.FacultyDetails;
import com.example.ErpApp.Model.StudentDetails;
import com.example.ErpApp.Model.UserModel;
import com.example.ErpApp.Repository.FacultyRepository;
import com.example.ErpApp.Repository.StudentRepository;
import com.example.ErpApp.Repository.UserRepository;
import com.example.ErpApp.Service.FacultyService;
import com.example.ErpApp.Service.StudentService;
import com.example.ErpApp.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
class UserControllerTest {
    private UserModel userModel;
    private StudentDetails studentDetails;
    @Mock
    private UserRepository userRepository;
    private FacultyDetails facultyDetails;
    @Mock
    private FacultyRepository facultyRepository;
    @InjectMocks
    private FacultyService facultyService;
    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private UserService userService;
    @Autowired
    private UserController userController;
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @BeforeEach
    void setUp() {
        userModel=UserModel.builder()
                .id(1L)
                .username("Sri")
                .email("sridharan@gmail.com")
                .password("sri123")
                .usertype("admin")
                        .build();
        facultyDetails= FacultyDetails.builder()
                .id(1L)
                .name("sri")
                .email("sridharan@gmail.com")
                .register("722818104143")
                .dob(new Date(25-06-2000))
                .doj(new Date(25-06-2000))
                .degree("ME")
                .userId(2)
                .build();
        studentDetails= StudentDetails.builder()
                .id(1l)
                .name("sri")
                .reg("722818104145")
                .email("sridharan@gmail.com")
                .userId(2L)
                .build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addUser() {
        given(userRepository.existsByEmail(userModel.getEmail())).willReturn(true);
        boolean userPresent=userService.existsByEmail("sridharan@gmail.com");
        given(userRepository.save(userModel)).willReturn(userModel);
        String result=userService.saveUser(userModel);
        assertEquals("Saved in Database",result);
        assertEquals(true,userPresent);
    }

    @Test
    void createAuthenticationToken() {
        given(userRepository.findByEmail(userModel.getEmail())).willReturn(userModel);
        given(userRepository.existsByEmail(userModel.getEmail())).willReturn(true);
        given(facultyRepository.findByEmail(userModel.getEmail())).willReturn(facultyDetails);
        given(facultyRepository.save(facultyDetails)).willReturn(facultyDetails);
        FacultyDetails result=facultyService.findByEmail(userModel.getEmail());
        Assertions.assertEquals(userModel.getEmail(),result.getEmail());
        given(studentRepository.findByEmail(userModel.getEmail())).willReturn(studentDetails);
        StudentDetails result1=studentService.findByEmail(userModel.getEmail());
        given(studentRepository.save(studentDetails)).willReturn(studentDetails);
        Assertions.assertEquals(userModel.getEmail(),result1.getEmail());

    }

    @Test
    void getAllUsers() {
        given(userRepository.findAll()).willReturn(List.of(userModel));
        List<UserModel> userModelList=userService.findAllUsers();
        for(UserModel users : userModelList) {
            assertThat(users).isNotNull();
        }
        assertThat(userModelList.size()).isEqualTo(1);
    }

    @Test
    void getAdmin() {
        given(userRepository.findById(1L)).willReturn(Optional.of(userModel));
        Optional<UserModel> admin =userService.findById(userModel.getId());
        assertThat(admin).isNotNull();
    }
}
