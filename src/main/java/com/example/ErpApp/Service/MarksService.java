package com.example.ErpApp.Service;

import com.example.ErpApp.Model.MarksModel;
import com.example.ErpApp.Repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarksService {

    @Autowired
    MarksRepository marksRepository;

    public Optional<MarksModel> findSubjectCountBySubjectName(String subject) {
        return marksRepository.findBySubject(subject);
    }

    public MarksModel saveMark(MarksModel marksFromDB) {
        return marksRepository.save(marksFromDB);
    }
}
