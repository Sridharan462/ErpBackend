package com.example.ErpApp.Repository;

import com.example.ErpApp.Model.FacultyDetails;
import com.example.ErpApp.Model.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentDetails,Long> {
    public StudentDetails findByEmail(String email);
    public StudentDetails findByuserId(Long id);
    public StudentDetails findByReg(String reg);
}
