package com.example.ErpApp.Repository;

import com.example.ErpApp.Model.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentDetails, Long> {
    public StudentDetails findByEmail(String email);

    public Optional<StudentDetails> findByuserId(Long id);
    public StudentDetails findById(Integer id);

    public StudentDetails findByReg(String reg);

    public boolean existsByEmail(String email);
}
