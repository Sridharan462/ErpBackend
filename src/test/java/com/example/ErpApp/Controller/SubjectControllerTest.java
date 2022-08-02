package com.example.ErpApp.Controller;

import com.example.ErpApp.Model.MarksModel;
import com.example.ErpApp.Model.StudentDetails;
import com.example.ErpApp.Model.Subjects;
import com.example.ErpApp.Repository.MarksRepository;
import com.example.ErpApp.Repository.StudentRepository;
import com.example.ErpApp.Repository.SubjectRepository;
import com.example.ErpApp.Service.MarksService;
import com.example.ErpApp.Service.StudentService;
import com.example.ErpApp.Service.SubjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
@RunWith(MockitoJUnitRunner.class)
class SubjectControllerTest {
    private StudentDetails studentDetails;
    private Subjects subjects;
    private MarksModel marksModel;
    @InjectMocks
    private SubjectService subjectService;
    @Mock
    private SubjectRepository subjectRepository;
    @InjectMocks
    private MarksService marksService;
    @Mock
    private MarksRepository marksRepository;
    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        subjects = Subjects.builder()
                .id(1L)
                .subjectName("EVS")
                .subjectCode("Ev44")
                .count(3)
                .mark(98)
                .register("722818104145")
                .markAdded(false)
                .build();
        marksModel = MarksModel.builder()
                .id(1L)
                .subject("EVS")
                .count(3)
                .build();
        studentDetails= StudentDetails.builder()
                .id(1l)
                .name("sri")
                .reg("722818104145")
                .email("gksri@gmail.com")
                .userId(2L)
                .build();
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void addSubject() {
        given(subjectRepository.save(subjects)).willReturn(subjects);
        Subjects subjects1=subjectService.saveSubject(subjects);
        assertThat(subjects1).isNotNull();
        given(marksRepository.findBySubject(subjects.getSubjectName())).willReturn(Optional.of(marksModel));
        MarksModel marks=marksService.findSubjectCountBySubjectName(subjects.getSubjectName()).get();
        Assertions.assertEquals(subjects1.getSubjectName(),marks.getSubject());
        given(marksRepository.save(marksModel)).willReturn(marksModel);
        MarksModel marksModel1=marksService.saveMark(marksModel);
        assertThat(marksModel1).isNotNull();
    }

    @Test
    void getSubject() {
        given(subjectRepository.findBySem(1)).willReturn(List.of(subjects.getSubjectName()));
        List<String> subjectsList=subjectService.findBySem(1);
        for(String subjects1 : subjectsList)
        {
            assertThat(subjects1).isNotNull();
        }

    }

    @Test
    void addMark() {
        given(marksRepository.findBySubject(subjects.getSubjectName())).willReturn(Optional.of(marksModel));
        MarksModel marksModel1 = marksService.findSubjectCountBySubjectName(subjects.getSubjectName()).get();
        assertThat(marksModel1).isNotNull();
        given(studentRepository.findByReg(studentDetails.getReg())).willReturn(studentDetails);
        Boolean studentServiceRegIfExists = studentService.findRegIfExists(studentDetails.getReg());
        Assertions.assertTrue(studentServiceRegIfExists);
        given(subjectRepository.findAllBySubjectName(subjects.getSubjectName())).willReturn(List.of(subjects));
        List<Subjects> subjectsList=subjectService.findAllData(subjects.getSubjectName());
        for (Subjects subjects1 : subjectsList) {
            Assertions.assertEquals(false, subjects1.isMarkAdded());
        }
        given(subjectRepository.findByRegisterAndSubjectName(subjects.getRegister(),subjects.getSubjectName())).willReturn(subjects);
        Boolean studentExist = subjectService.findByRegisterAndSubjectName(subjects.getRegister(),subjects.getSubjectName());
        Assertions.assertTrue(true);
        given(subjectRepository.save(subjects)).willReturn(subjects);
        Subjects subjects1=subjectService.saveMarks(subjects);
        assertThat(subjects1).isNotNull();
        given(marksRepository.save(marksModel)).willReturn(marksModel);
        MarksModel subjects=marksService.saveMark(marksModel);
        assertThat(subjects).isNotNull();

    }

    @Test
    void getMark() {
        given(subjectRepository.getMark(subjects.getSem(),subjects.getRegister())).willReturn(List.of(subjects));
        List<Subjects> subjects1=subjectService.getMark(subjects.getSem(),subjects.getRegister());
        Assertions.assertNotNull(subjects1);

    }
}