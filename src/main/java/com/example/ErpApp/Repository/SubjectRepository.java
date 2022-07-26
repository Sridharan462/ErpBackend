package com.example.ErpApp.Repository;

import com.example.ErpApp.Model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subjects, Long> {
    String rawQuery = "select DISTINCT subject_name from Subject where sem = :id";
    String Query1 = "select * from Subject where sem = :sem and register = :Register";
    String Query2 = "update Subject set mark = :mark where subjectName = :subjectName and register = :Register";

    public Subjects findByRegisterAndSubjectName(String register,String subjectName);

    public List<Subjects> findAllBySubjectName(String subject);

    public Boolean existsBySubjectName(String sname);

    @Query(nativeQuery = true, value = rawQuery)
    public List<String> findBySem(@Param("id") int id);

    @Query(nativeQuery = true, value = Query1)
    public List<Subjects> getMark(@Param("sem") int sem, @Param("Register") String register);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = Query2)
    public void updateMark(@Param("mark") int mark, @Param("subjectName") String subjectName, @Param("Register") String Register);

}
