package com.example.ErpApp.Controller;

import com.example.ErpApp.Model.StudentDetails;
import com.example.ErpApp.Repository.StudentRepository;
import com.example.ErpApp.Service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {
    Logger logger = LoggerFactory.getLogger(StudentDetails.class);
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody StudentDetails studentDetails) {
        if(!studentService.existsByEmail(studentDetails.getEmail())) {
            studentService.saveStudent(studentDetails);
            return "Added";
        }
        else
            return "Already found";
    }

    @GetMapping("/getStudent/{id}")
    public Optional<StudentDetails> getStudent(@PathVariable Long id) {
        return studentService.findByuserId(id);
    }

    @GetMapping("/getAllStudent")
    public List<StudentDetails> getAllStudent() {
        return studentService.findAll();
    }

    @GetMapping("/particularStudent/{id}")
    public Optional<StudentDetails> particularStudent(@PathVariable Long id) {
        logger.info("User present");
        return studentService.findById(id);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String delete(@PathVariable Long id) {
        StudentDetails delete = studentService.findById(id).get();
        studentService.deleteById(delete);
        return "deleted";
    }

    @PutMapping("/editStudent/{id}")
    public ResponseEntity<StudentDetails> updateService(@PathVariable Long id, @RequestBody StudentDetails studentDetails) {
        StudentDetails studentDetails1 = studentService.findById(id).get();
        studentDetails1.setEmail(studentDetails.getEmail());
        studentDetails1.setName(studentDetails.getName());
        studentDetails1.setReg(studentDetails.getReg());
        studentDetails1.setCourse(studentDetails.getCourse());
        studentDetails1.setDob(studentDetails.getDob());
        studentDetails1.setAadharnumber(studentDetails.getAadharnumber());
        studentDetails1.setNumber(studentDetails.getNumber());
        studentDetails1.setAddress(studentDetails.getAddress());
        studentDetails1.setBlood(studentDetails.getBlood());
        studentDetails1.setCaste(studentDetails.getCaste());
        studentDetails1.setCommunity(studentDetails.getCommunity());
        studentDetails1.setFmail(studentDetails.getFmail());
        studentDetails1.setFnum(studentDetails.getFnum());
        studentDetails1.setFname(studentDetails.getFname());
        studentDetails1.setInfo(studentDetails.getInfo());
        studentDetails1.setInfo1(studentDetails.getInfo1());
        studentDetails1.setNationality(studentDetails.getNationality());
        studentDetails1.setRoll(studentDetails.getRoll());
        studentDetails1.setReligion(studentDetails.getReligion());
        studentDetails1.setGender(studentDetails.getGender());
        studentDetails1.setMname(studentDetails.getMname());
        studentDetails1.setMnum(studentDetails.getMnum());
        studentDetails1.setLang(studentDetails.getLang());
        StudentDetails updatedServices = studentService.saveStudent(studentDetails1);
        return ResponseEntity.ok(updatedServices);

    }
}
