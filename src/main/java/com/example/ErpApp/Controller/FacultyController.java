package com.example.ErpApp.Controller;

import com.example.ErpApp.Model.FacultyDetails;
import com.example.ErpApp.Repository.FacultyRepository;
import com.example.ErpApp.Service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class FacultyController {
    Logger logger = LoggerFactory.getLogger(FacultyDetails.class);
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private FacultyRepository facultyRepository;

    @PostMapping("/addFaculty")
    public String addFaculty(@RequestBody FacultyDetails facultyDetails) {
        facultyRepository.save(facultyDetails);
        return "faculty saved";
    }

    @GetMapping("/getFaculty/{id}")
    public FacultyDetails getFaculty(@PathVariable Long id) {
        logger.info("Id in database");
        return facultyService.findByuserId(id);
    }

    @GetMapping("/getAllFaculty")
    public List<FacultyDetails> allFaculty() {
        return facultyRepository.findAll();
    }

    @GetMapping("/particularStaff/{id}")
    public Optional<FacultyDetails> particularStaff(@PathVariable Long id) {
        return facultyRepository.findById(id);
    }

    @DeleteMapping("/deleteFaculty/{id}")
    public String delete(@PathVariable Long id) {
        FacultyDetails delete = facultyService.findById(id);
        facultyRepository.delete(delete);
        return "deleted";
    }

    @PutMapping("/editFaculty/{id}")
    public ResponseEntity<FacultyDetails> updateService(@PathVariable Long id, @RequestBody FacultyDetails facultyDetails) {
        FacultyDetails facultyDetails1 = facultyService.findById(id);
        facultyDetails1.setEmail(facultyDetails.getEmail());
        facultyDetails1.setName(facultyDetails.getName());
        facultyDetails1.setRegister(facultyDetails.getRegister());
        facultyDetails1.setDegree(facultyDetails.getDegree());
        facultyDetails1.setDob(facultyDetails.getDob());
        facultyDetails1.setDoj(facultyDetails.getDoj());
        facultyDetails1.setNumber(facultyDetails.getNumber());
        FacultyDetails updatedServices = facultyRepository.save(facultyDetails1);
        return ResponseEntity.ok(updatedServices);

    }
}
