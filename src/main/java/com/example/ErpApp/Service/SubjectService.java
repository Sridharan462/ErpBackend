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

    public Boolean findByRegister(String register) {
        Subjects subjects = subjectRepository.findByRegister(register);
        return subjects != null && !subjects.isMarkAdded();
    }

    public List<Subjects> findAllData(String sub) {
        return subjectRepository.findAllBySubjectName(sub);
    }
//    public Subjects findByRegister(String register)
//    {
//        Subjects sub= subjectRepository.findByRegister(register);
//        return sub;
//    }
//    public Subjects existsBySubjectname(String sname)
//    {
//        return subjectRepository.existsBySubjectname(sname);
//    }
}
