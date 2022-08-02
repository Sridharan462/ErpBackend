package com.example.ErpApp.Repository;

import com.example.ErpApp.Model.FacultyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<FacultyDetails, Long> {
    public FacultyDetails findByEmail(String email);

    public FacultyDetails findByuserId(Long id);

   public Boolean existsByEmail(String email);
}
