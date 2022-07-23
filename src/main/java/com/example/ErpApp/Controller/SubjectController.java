package com.example.ErpApp.Controller;

import com.example.ErpApp.Model.Subjects;
import com.example.ErpApp.Repository.SubjectRepository;
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
    @PostMapping("/addSubject")
    public String addSubject(@RequestBody Subjects subjects)
    {

        for(int i=1;i<=subjects.getCount();i++) {

            Subjects s=new Subjects(subjects);
            subjectRepository.save(s);
            System.out.println(subjects);
        }
        return "subject added";
    }
    int Sid=0;
    @GetMapping("/getSubject/{id}")
    public List<Subjects> getSubject(@PathVariable int id)
    {
        return subjectRepository.findBySem(id);
    }
    int n=0;
    int m=1;
    int temp=0;
    int temp1=1;
    @PostMapping("/addMark")
    public String addMark(@RequestBody Subjects subjects)
    {
        if(!subjectRepository.existsByRegister(subjects.getRegister()))
        {
            Sid++;
            System.out.println(!subjectRepository.existsByRegister(subjects.getRegister()));
            System.out.println(Sid + "of 1st if");
        }
        temp=Sid;
        if( (temp1==1 && Sid==1) || (!subjectRepository.existsByRegister(subjects.getRegister()))) {

            Subjects sub = subjectService.findById(Sid);
            sub.setRegister(subjects.getRegister());
            subjectRepository.save(sub);
            n=0;
            temp1++;
            System.out.println(temp1 +"temp1");
            subjectRepository.updateMark(subjects.getMark(), subjects.getSubjectname(), subjects.getRegister());
            return "updated";
        }
        else
        {
            String reg=subjects.getRegister();
            System.out.println(temp);
            Subjects sub = subjectService.findById(temp+n);
            m=temp+n;
            System.out.println("n"+ n);
            for(int i=2;i<=sub.getCount()+m;i++) {
                n++;
                Subjects sub1 = subjectService.findById(i);
                int mark= sub1.getMark();
                System.out.println(n + "n");
                if(mark==0) {
                    System.out.println(mark + "mark");
                    sub1.setRegister(subjects.getRegister());
                    subjectRepository.save(sub1);

                    if (sub1.getRegister().equals(subjects.getRegister()) && sub1.getSubjectname().equals(subjects.getSubjectname())) {
                        subjectRepository.updateMark(subjects.getMark(), subjects.getSubjectname(), subjects.getRegister());
                        break;
                    }
                }
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
