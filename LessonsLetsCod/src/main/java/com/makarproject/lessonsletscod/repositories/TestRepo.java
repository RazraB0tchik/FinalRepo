package com.makarproject.lessonsletscod.repositories;

import com.makarproject.lessonsletscod.entity.Questions;
import com.makarproject.lessonsletscod.entity.Tematik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepo extends JpaRepository<Tematik, Long> {

    Tematik findAllByLanguage(String lang);
}
