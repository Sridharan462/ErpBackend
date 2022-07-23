package com.example.ErpApp.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Subject")
@NoArgsConstructor
public class Subjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int sem;
    private String subjectName;
    private String subjectCode;
    private int mark;
    private String register;
    private int count;
    private boolean markAdded;

    public Subjects(Subjects subjects) {
        this.id = subjects.getId();
        this.sem = subjects.getSem();
        this.subjectName = subjects.getSubjectName();
        this.subjectCode = subjects.getSubjectCode();
        this.count = subjects.getCount();
    }

    @Override
    public String toString() {
        return "Subjects{" +
                "sem=" + sem +
                ", subjectName='" + subjectName + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", mark=" + mark +
                ", register='" + register + '\'' +
                ", count=" + count +
                ", markAdded=" + markAdded +
                '}';
    }
}
