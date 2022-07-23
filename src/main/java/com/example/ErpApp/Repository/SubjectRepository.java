package com.example.ErpApp.Repository;

import com.example.ErpApp.Model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface SubjectRepository extends JpaRepository<Subjects,Long> {
    public Subjects findByRegister(String register);
    public List<Subjects> findAllBySubjectName(String subject);
    public Boolean existsBySubjectName(String sname);

    String rawQuery="select * from Subject where sem = :id";
    @Query(nativeQuery = true,value = rawQuery)
    public List<Subjects> findBySem(@Param("id") int id);

    String Query1="select * from Subject where sem = :sem and register = :Register";
    @Query(nativeQuery = true,value = Query1)
    public  List<Subjects> getMark(@Param("sem") int sem, @Param("Register") String register);

    String Query2="update Subject set mark = :mark where subjectName = :subjectName and register = :Register";
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = Query2)
    public void updateMark(@Param("mark") int mark, @Param("subjectName") String subjectName,@Param("Register") String Register);

}
