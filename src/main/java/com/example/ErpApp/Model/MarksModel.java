package com.example.ErpApp.Model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DynamicUpdate
@Table(name = "marksmodel")
public class MarksModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    int count;
    String subject;

    public MarksModel() {
    }

    public MarksModel(long id, int count, String subject) {
        this.id = id;
        this.count = count;
        this.subject = subject;
    }
    @Builder(builderMethodName = "builder")
    public static MarksModel marks(long id,int count,String subject) {
        return new MarksModel(id, count, subject);
    }

}
