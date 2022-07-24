package com.example.ErpApp.Service;

import com.example.ErpApp.Model.FacultyDetails;
import com.example.ErpApp.Repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public FacultyDetails findByEmail(String email) {
        return facultyRepository.findByEmail(email);
    }

    public FacultyDetails findByuserId(Long id) {
        return facultyRepository.findByuserId(id);
    }

    public FacultyDetails findById(Long id) {
        Optional<FacultyDetails> faculty = facultyRepository.findById(id);
        FacultyDetails facultyDetails = faculty.get();
        return facultyDetails;
    }
}
