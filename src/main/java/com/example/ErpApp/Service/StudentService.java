package com.example.ErpApp.Service;

import com.example.ErpApp.Model.StudentDetails;
import com.example.ErpApp.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDetails findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Optional<StudentDetails> findByuserId(Long id) {
        return studentRepository.findByuserId(id);
    }

    public Optional<StudentDetails> findById(Long id) {
        Optional<StudentDetails> student = studentRepository.findById(id);
        return student;
    }

    public Boolean findRegIfExists(String reg) {
        return studentRepository.findByReg(reg) != null;
    }

    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    public StudentDetails saveStudent(StudentDetails studentDetails) {
        studentRepository.save(studentDetails);
        return studentDetails;
    }

    public List<StudentDetails> findAll() {
        return studentRepository.findAll();
    }

    public void deleteById(StudentDetails delete) {
        studentRepository.deleteById(delete.getId());
    }
}
