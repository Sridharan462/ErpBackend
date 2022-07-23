package com.example.ErpApp.Repository;

import com.example.ErpApp.Model.MarksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MarksRepository extends JpaRepository<MarksModel, Long> {

    String updateQueryForSubjectCount = "update MarksModel set count = ?1 where subject = '?2'";

    public MarksModel findBySubject(String subject);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update MarksModel set count = '?1' where id = '?2'")
    void updateSubjectCount(@Param("count") int count,@Param("id") Long id);
}
