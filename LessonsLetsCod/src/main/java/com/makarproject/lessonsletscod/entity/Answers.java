package com.makarproject.lessonsletscod.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "answersjava")
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Column(name = "answer")
    public String answer;

    @Column(name="correct_answer")
    public boolean correctAnswer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qwestion_id")
    private Questions questions;

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    public Answers(int i, String answer, boolean correctAnswer, int questionId) {
        this.answer = answer;
        this.correctAnswer = correctAnswer;
    }

    public Answers() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


    @Override
    public String toString() {
        return "Answers{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
