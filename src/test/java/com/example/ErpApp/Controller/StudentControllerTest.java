package com.example.ErpApp.Controller;

import com.example.ErpApp.Model.FacultyDetails;
import com.example.ErpApp.Model.StudentDetails;
import com.example.ErpApp.Repository.FacultyRepository;
import com.example.ErpApp.Repository.StudentRepository;
import com.example.ErpApp.Service.FacultyService;
import com.example.ErpApp.Service.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
class StudentControllerTest {
private StudentDetails studentDetails;
@InjectMocks
private StudentService studentService;
@Mock
private StudentRepository studentRepository;
    @BeforeEach
    void setUp() {
        studentDetails=StudentDetails.builder()
                .id(1l)
                .name("sri")
                .reg("722818104145")
                .email("gksri@gmail.com")
                .userId(2L)
                        .build();
       MockitoAnnotations.initMocks(this);
    }

    @Test
    void addStudent() {
        given(studentRepository.findByEmail(studentDetails.getEmail()))
        .willReturn(studentDetails);
        given(studentRepository.save(studentDetails)).willReturn(studentDetails);
        StudentDetails studentDetails1=studentService.saveStudent(studentDetails);
        assertThat(studentDetails1).isNotNull();
    }

    @Test
    void getStudent() {
        given(studentRepository.findByuserId(studentDetails.getUserId())).willReturn(Optional.of(studentDetails));
        StudentDetails studentDetails1=studentService.findByuserId(studentDetails.getUserId()).get();
        assertThat(studentDetails1).isNotNull();
    }

    @Test
    void getAllStudent() {
        given(studentRepository.findAll()).willReturn(List.of(studentDetails));
        List<StudentDetails> studentDetailsList=studentService.findAll();
        for(StudentDetails studentDetail : studentDetailsList) {
            assertThat(studentDetail).isNotNull();
        }
        assertThat(studentDetailsList.size()).isEqualTo(1);
    }

    @Test
    void particularStudent() {
        given(studentRepository.findById(studentDetails.getId())).willReturn(Optional.of(studentDetails));
        StudentDetails studentDetails1=studentService.findById(studentDetails.getId()).get();
        assertThat(studentDetails1).isNotNull();
    }

    @Test
    void delete() {
     willDoNothing().given(studentRepository).deleteById(studentDetails.getId());
     studentService.deleteById(studentDetails);
     assertEquals(false, studentRepository.existsById(studentDetails.getId()));
    }

    @Test
    void updateService() {
        given(studentRepository.findById(studentDetails.getId())).willReturn(Optional.of(studentDetails));
        StudentDetails savedFaculty = studentService.findById(studentDetails.getId()).get();
        savedFaculty.setEmail("student@gmail.com");
        savedFaculty.setName("SriR");
        StudentDetails updatedEmployee = studentService.saveStudent(savedFaculty);
        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("student@gmail.com");
        Assertions.assertThat(updatedEmployee.getName()).isEqualTo("SriR");
    }
}