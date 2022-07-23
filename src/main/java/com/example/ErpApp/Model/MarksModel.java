package com.example.ErpApp.Model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Data
@DynamicUpdate
@Table(name = "marksmodel")
public class MarksModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    int count;
    String subject;
}
