package com.example.ErpApp.Controller;

import com.example.ErpApp.Model.StudentDetails;
import com.example.ErpApp.Model.Subjects;
import com.example.ErpApp.Repository.StudentRepository;
import com.example.ErpApp.Repository.SubjectRepository;
import com.example.ErpApp.Service.StudentService;
import com.example.ErpApp.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;
    @PostMapping("/addSubject")
    public String addSubject(@RequestBody Subjects subjects)
    {
        for(long i=1;i<=subjects.getCount();i++) {
            StudentDetails student=studentService.findById(i);
            System.out.println(i);
            System.out.println(student.getReg());
            Subjects s=new Subjects(subjects);
            s.setRegister(student.getReg());
            subjectRepository.save(s);
            System.out.println(s);
        }
        return "subject added";
    }
    @GetMapping("/getSubject/{id}")
    public List<Subjects> getSubject(@PathVariable int id)
    {
        return subjectRepository.findBySem(id);
    }
    @PostMapping("/addMark")
    public String addMark(@RequestBody Subjects subjects)
    {
        for(long i=1;i<=i;i++)
        {
            Subjects subject=subjectService.findById(i);
            if(subject.getRegister().equals(subjects.getRegister()) && subject.getSubjectname().equals(subjects.getSubjectname()))
            {
                subjectRepository.updateMark(subjects.getMark(),subjects.getSubjectname(),subjects.getRegister());
                break;
            }
        }
        return "";
    }
    @PostMapping("/getMark")
    public List<Subjects> getMark(@RequestBody Subjects subjects)
    {
       return subjectRepository.getMark(subjects.getSem(),subjects.getRegister());
    }
}
