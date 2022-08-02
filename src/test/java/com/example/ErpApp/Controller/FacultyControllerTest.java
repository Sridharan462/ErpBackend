package com.example.ErpApp.Controller;

import com.example.ErpApp.Model.FacultyDetails;
import com.example.ErpApp.Repository.FacultyRepository;
import com.example.ErpApp.Service.FacultyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
class FacultyControllerTest {
    private FacultyDetails facultyDetails;
    @InjectMocks
    private FacultyService facultyService;
    @Mock
    private FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(FacultyDetails.class);
    @BeforeEach
    void setUp() {
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
           MockitoAnnotations.initMocks(this);
    }

    @Test
    void addFaculty() throws Exception {
        given(facultyRepository.findByEmail(facultyDetails.getEmail()))
                .willReturn(facultyDetails);
        given(facultyService.saveUser(facultyDetails)).willReturn(facultyDetails);
        logger.info("email" +facultyDetails.getEmail());
        FacultyDetails service= facultyService.saveUser(facultyDetails);
//        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
//            facultyService.saveUser(facultyDetails);
//        });
        logger.info("model"+service.getEmail());
        assertTrue(!facultyRepository.existsByEmail(facultyDetails.getEmail()));
        assertThat(service).isNotNull();
    }

    @Test
    void getFaculty() {
        given(facultyRepository.findByuserId(facultyDetails.getUserId())).willReturn(facultyDetails);
        FacultyDetails facultyDetails1=facultyService.findByuserId(facultyDetails.getUserId());
        assertThat(facultyDetails1).isNotNull();
    }

    @Test
    void allFaculty() {
        given(facultyRepository.findAll()).willReturn(List.of(facultyDetails));
        List<FacultyDetails> facultyDetailsList = facultyService.findAll();
        logger.info("email" +facultyDetailsList);
        for(FacultyDetails facultyDetails1:facultyDetailsList) {
            assertThat(facultyDetails1).isNotNull();
        }
        assertThat(facultyDetailsList.size()).isEqualTo(1);
    }

    @Test
    void particularStaff() {
        given(facultyRepository.findById(facultyDetails.getId())).willReturn(Optional.of(facultyDetails));
        FacultyDetails savedFaculty = facultyService.findById(facultyDetails.getId()).get();
        logger.info("email "+savedFaculty.getEmail());
        assertThat(savedFaculty).isNotNull();
    }

    @Test
    void delete() {
        willDoNothing().given(facultyRepository).deleteById(facultyDetails.getId());
        facultyService.deleteById(facultyDetails.getId());
        verify(facultyRepository, times(1)).deleteById(facultyDetails.getId());

    }

    @Test
    void updateService() {
       given(facultyRepository.findById(facultyDetails.getId())).willReturn(Optional.of(facultyDetails));
        FacultyDetails savedFaculty = facultyService.findById(facultyDetails.getId()).get();
        savedFaculty.setEmail("fac@gmail.com");
        savedFaculty.setName("SriR");
        FacultyDetails updatedEmployee = facultyService.saveUser(savedFaculty);
        assertThat(updatedEmployee.getEmail()).isEqualTo("fac@gmail.com");
        assertThat(updatedEmployee.getName()).isEqualTo("SriR");
    }
}
