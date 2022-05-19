package com.makarproject.lessonsletscod.services;

import com.makarproject.lessonsletscod.entity.Answers;
import com.makarproject.lessonsletscod.entity.Questions;
import com.makarproject.lessonsletscod.entity.Tematik;
import com.makarproject.lessonsletscod.repositories.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Repository
public class TestServiceJAVA {


    @Autowired
    TestRepo testRepo;

    public String getCurrentUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    HashMap<String, List<Questions>> listHashMap = new HashMap<>();
    List<Questions> list = new ArrayList<>();
    List<Answers> listAns = new ArrayList<>();
    int scetcik = 1;
    int countOfQuestions = 0;
    int trueAnswers = 0;
    int countStop = 0;
    public void addQuestions(){
        Tematik tem =  testRepo.findAllByLanguage("JAVA");
        list = tem.getQuestionsList();
        listHashMap.put(getCurrentUsername(), list);
        countOfQuestions=list.size();
    }
    public String getQuestion(){
        int count=(int)Math.random()*list.size();
        Questions question = list.get(count);
        scetcik+=1;
        countStop=(countOfQuestions+1)-list.size();
        listAns = question.getAnswersList();
        list.remove(count);
        listHashMap.replace(getCurrentUsername(), list);
        System.out.println("asd" + " "+countStop);
        return question.getQwestions();
    }
    public List<Answers> getAnswers(){
        return listAns;
    }

    public int getCountAnswer(){
        return scetcik;
    }
    public int getSizeOfTest(){
        return countOfQuestions;
    }

    public int chetBalls(String answer){
        int itog = Integer.parseInt(answer);
        if(listAns.get(itog).correctAnswer == true){
            trueAnswers++;
        }
        return trueAnswers;
    }

    public int stopTest(){
        if(countStop==5){
            scetcik=0;
            return 0;
        }
        return countStop;
    }
    public double getProcentCoot(){
        double result = trueAnswers%countOfQuestions*10;
        return result;
    }
}
