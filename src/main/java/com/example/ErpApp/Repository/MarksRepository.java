package com.example.ErpApp.Repository;

import com.example.ErpApp.Model.MarksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarksRepository extends JpaRepository<MarksModel, Long> {


    public Optional<MarksModel> findBySubject(String subject);
}
