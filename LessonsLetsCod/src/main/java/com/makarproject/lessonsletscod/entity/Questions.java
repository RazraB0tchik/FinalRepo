package com.makarproject.lessonsletscod.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Set;

@Component
@Entity
@Table(name = "qwestionsjava")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Column(name = "qwestion")
    public String qwestions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questions", fetch = FetchType.EAGER)
    private List<Answers> answersList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tem")
    private Tematik tematik;

    public Tematik getTematik() {
        return tematik;
    }

    public void setTematik(Tematik tematik) {
        this.tematik = tematik;
    }

    public List<Answers> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<Answers> answersList) {
        this.answersList = answersList;
    }

    public Questions(String qwestions) {
        this.qwestions = qwestions;
    }

    public Questions() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQwestions() {
        return qwestions;
    }

    public void setQwestions(String qwestions) {
        this.qwestions = qwestions;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id='" + id + '\'' +
                ", qwestions='" + qwestions + '\'' +
                '}';
    }
}
