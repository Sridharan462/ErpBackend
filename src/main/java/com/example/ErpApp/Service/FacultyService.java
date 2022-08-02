package com.example.ErpApp.Service;

import com.example.ErpApp.Model.FacultyDetails;
import com.example.ErpApp.Repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public FacultyDetails findByEmail(String email) {
        return facultyRepository.findByEmail(email);
    }
    public Boolean existsByEmail(String email) {
        return facultyRepository.existsByEmail(email);
    }

    public FacultyDetails findByuserId(Long id) {
        return facultyRepository.findByuserId(id);
    }
    public FacultyDetails saveUser(FacultyDetails user) {
        facultyRepository.save(user);
        return user;
    }
    public Optional<FacultyDetails> findById(Long id) {
        Optional<FacultyDetails> faculty = facultyRepository.findById(id);
        return faculty;
    }

    public void deleteById(Long id) {
        facultyRepository.deleteById(id);
    }

    public List<FacultyDetails> findAll() {
        return facultyRepository.findAll();
    }
}
