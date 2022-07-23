package com.example.ErpApp.Controller;

import com.example.ErpApp.Model.MarksModel;
import com.example.ErpApp.Model.Subjects;
import com.example.ErpApp.Repository.MarksRepository;
import com.example.ErpApp.Repository.StudentRepository;
import com.example.ErpApp.Repository.SubjectRepository;
import com.example.ErpApp.Service.MarksService;
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
    private StudentService studentService;
    @Autowired
    MarksRepository marksRepository;
    @Autowired
    MarksService marksService;


    @PostMapping("/addSubject")
    public String addSubject(@RequestBody Subjects subjects) {

        MarksModel marksModel = new MarksModel();
        for (int i = 1; i <= subjects.getCount(); i++) {
            Subjects s = new Subjects(subjects);
            s.setMarkAdded(false);
            subjectRepository.save(s);
            System.out.println(subjects);
        }
        MarksModel marksFromDB = marksRepository.findBySubject(subjects.getSubjectName());
        if (marksFromDB != null) {
            marksFromDB.setCount(subjects.getCount() + marksFromDB.getCount());
            marksRepository.save(marksFromDB);
//            marksService.updateCountOfSubject(finalCount, marksFromDB.getId());
        } else {
            marksModel.setSubject(subjects.getSubjectName());
            marksModel.setCount(subjects.getCount());
            marksRepository.save(marksModel);
        }
        return "subject added";
    }

    @GetMapping("/getSubject/{id}")
    public List<Subjects> getSubject(@PathVariable int id) {
        return subjectRepository.findBySem(id);
    }

    @PostMapping("/addMark")
    public String addMark(@RequestBody Subjects subjects) {
        MarksModel model = marksService.findSubjectCountBySubjectName(subjects.getSubjectName());
        if (!studentService.findRegIfExists(subjects.getRegister())) {
            System.out.println("#error");
            return "Add a valid register number";
        } else if (!subjectService.findByRegister(subjects.getRegister()) && model.getCount() >= 0) {
            Subjects marksFromRequest = new Subjects();
            List<Subjects> marksFromRequestList = subjectService.findAllData(subjects.getSubjectName());
            boolean flagForMarkAddedCheck = true;
            for (Subjects subject : marksFromRequestList) {
                if (!subject.isMarkAdded()) {
                    marksFromRequest = subject;
                    flagForMarkAddedCheck = true;
                    break;
                } else flagForMarkAddedCheck = false;
            }
            if (!flagForMarkAddedCheck)
                return "Un-applicable to add mark";
            marksFromRequest.setCount(marksFromRequest.getCount() - 1);
            marksFromRequest.setMarkAdded(true);
            marksFromRequest.setRegister(subjects.getRegister());
            marksFromRequest.setMark(subjects.getMark());
            subjectRepository.save(marksFromRequest);
            model.setCount(model.getCount() - 1);
            marksRepository.save(model);
            return marksFromRequest.toString();
        } else {
            return "Marks were added for all the students";
        }
    }

    @PostMapping("/getMark")
    public List<Subjects> getMark(@RequestBody Subjects subjects) {
        return subjectRepository.getMark(subjects.getSem(), subjects.getRegister());
    }
}
