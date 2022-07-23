package com.example.ErpApp.Model;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "Subject")
@NoArgsConstructor
public class Subjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int sem;
    private String subjectname;
    private String subjectcode;
    private int mark;
    private String register;
    private int count;

    public Subjects(Subjects subjects) {
        this.id=subjects.getId();
        this.sem=subjects.getSem();
        this.subjectname=subjects.getSubjectname();
        this.subjectcode=subjects.getSubjectcode();
        this.count=subjects.getCount();
    }
}
