package com.example.ErpApp.Repository;

import com.example.ErpApp.Model.MarksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksRepository extends JpaRepository<MarksModel, Long> {


    public MarksModel findBySubject(String subject);
}
