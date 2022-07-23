package com.example.ErpApp.Service;

import com.example.ErpApp.Model.MarksModel;
import com.example.ErpApp.Repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarksService {

    @Autowired
    MarksRepository marksRepository;

    public MarksModel findSubjectCountBySubjectName(String subject) {
        return marksRepository.findBySubject(subject);
    }

    public void updateCountOfSubject(int count, Long id) {
        System.out.println(count);
        marksRepository.updateSubjectCount(count, id);
    }
}
