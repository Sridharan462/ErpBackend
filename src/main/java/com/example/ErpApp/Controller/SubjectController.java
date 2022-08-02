package com.example.ErpApp.Controller;

import com.example.ErpApp.Model.MarksModel;
import com.example.ErpApp.Model.Subjects;
import com.example.ErpApp.Repository.MarksRepository;
import com.example.ErpApp.Repository.SubjectRepository;
import com.example.ErpApp.Service.MarksService;
import com.example.ErpApp.Service.StudentService;
import com.example.ErpApp.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class SubjectController {
    @Autowired
    MarksRepository marksRepository;
    @Autowired
    MarksService marksService;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/addSubject")
    public String addSubject(@RequestBody Subjects subjects) {
        MarksModel marksModel = new MarksModel();
        for (int subjectCount = 1; subjectCount <= subjects.getCount(); subjectCount++) {
            Subjects s = new Subjects(subjects);
            s.setMarkAdded(false);
             subjectService.saveSubject(s);
            System.out.println(subjects);
        }
        MarksModel marksFromDB = marksService.findSubjectCountBySubjectName(subjects.getSubjectName()).get();
        if (marksFromDB != null) {
            marksFromDB.setCount(subjects.getCount() + marksFromDB.getCount());
            marksService.saveMark(marksFromDB);
        } else {
            marksModel.setSubject(subjects.getSubjectName());
            marksModel.setCount(subjects.getCount());
            marksService.saveMark(marksModel);
        }
        return "subject add";
    }

    @GetMapping("/getSubject/{id}")
    public List<String> getSubject(@PathVariable int id) {
        return  subjectService.findBySem(id);
    }

    @PostMapping("/addMark")
    public String addMark(@RequestBody Subjects subjects) {
        MarksModel model = marksService.findSubjectCountBySubjectName(subjects.getSubjectName()).get();
        System.out.println(model);
        System.out.println(subjects);
        if (!studentService.findRegIfExists(subjects.getRegister())) {
            System.out.println("#error");
            return "Add a valid register number";
        } else if (!subjectService.findByRegisterAndSubjectName(subjects.getRegister(),subjects.getSubjectName()) && model.getCount() >= 0) {
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
            if (!flagForMarkAddedCheck){
                return "Un-applicable to add mark";}
//            marksFromRequest.setCount(marksFromRequest.getCount() - 1);
            marksFromRequest.setMarkAdded(true);
            marksFromRequest.setRegister(subjects.getRegister());
            marksFromRequest.setMark(subjects.getMark());
            subjectService.saveMarks(marksFromRequest);
            model.setCount(model.getCount() - 1);
            marksService.saveMark(model);
            return marksFromRequest.toString();
        } else {
            return "Marks were already added for  the student";
        }
    }

    @PostMapping("/getMark")
    public List<Subjects> getMark(@RequestBody Subjects subjects) {
        return subjectService.getMark(subjects.getSem(), subjects.getRegister());
    }
}

