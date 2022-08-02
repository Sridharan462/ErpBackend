package com.example.ErpApp.Model;

import lombok.Builder;
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

    public Subjects(long id, int sem, String subjectName, String subjectCode, int mark, String register, int count, boolean markAdded) {
        this.id = id;
        this.sem = sem;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.mark = mark;
        this.register = register;
        this.count = count;
        this.markAdded = markAdded;
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
    @Builder(builderMethodName = "builder")
    public static Subjects newUser(long id, int sem, String subjectName, String subjectCode, int mark, String register, int count, boolean markAdded) {
        return new Subjects(id,sem,subjectName,subjectCode,mark,register,count,markAdded);
    }
}
