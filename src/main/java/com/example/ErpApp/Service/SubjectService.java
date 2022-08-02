package com.example.ErpApp.Service;

import com.example.ErpApp.Model.Subjects;
import com.example.ErpApp.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subjects findById(long id) {
        Optional<Subjects> sub = subjectRepository.findById(id);
        Subjects subjects = sub.get();
        return subjects;
    }

    public Boolean findByRegisterAndSubjectName(String register,String subjectName) {
        Subjects subjects = subjectRepository.findByRegisterAndSubjectName(register,subjectName);
        System.out.println(subjects + " sub");
        return subjects != null && subjects.isMarkAdded();
    }

    public List<Subjects> findAllData(String sub) {
        return subjectRepository.findAllBySubjectName(sub);
    }

    public Subjects saveSubject(Subjects subject) {
        return subjectRepository.save(subject);
    }

    public List<String> findBySem(int id) {
        return subjectRepository.findBySem(id);
    }

    public Subjects saveMarks(Subjects marksFromRequest) {
        return subjectRepository.save(marksFromRequest);
    }

    public List<Subjects> getMark(int sem, String register) {
        return subjectRepository.getMark(sem,register);

    }
}
