package com.example.ErpApp.Service;

import com.example.ErpApp.Model.StudentDetails;
import com.example.ErpApp.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDetails findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public StudentDetails findByuserId(Long id) {
        return studentRepository.findByuserId(id);
    }

    public StudentDetails findById(Long id) {
        Optional<StudentDetails> student = studentRepository.findById(id);
        StudentDetails studentDetails = student.get();
        return studentDetails;
    }

    public Boolean findRegIfExists(String reg) {
        return studentRepository.findByReg(reg) != null;
    }

}
