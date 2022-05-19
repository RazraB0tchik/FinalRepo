package com.makarproject.lessonsletscod.repositories;

import com.makarproject.lessonsletscod.entity.Answers;
import com.makarproject.lessonsletscod.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QwestionRepo extends JpaRepository<Questions, Long> {
//    Answers findAnswersById(int id);

    Questions findQuestionsById(int id);

//    List<String> findAllByQwestions();
}