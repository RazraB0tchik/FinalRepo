package com.makarproject.lessonsletscod.controller;

import com.makarproject.lessonsletscod.repositories.QwestionRepo;
import com.makarproject.lessonsletscod.repositories.TestRepo;
import com.makarproject.lessonsletscod.services.TestServiceJAVA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MyController {

    @Autowired
    QwestionRepo qwestionRepo;

    @Autowired
    TestRepo testRepo;

    @Autowired
    TestServiceJAVA testServiceJAVA;

    @GetMapping("/allUser")
    public String getAll(){
        return ("index");
    }

    @GetMapping("/aboba")
    public String getAb(){
        return ("aboba");
    }

    @GetMapping("/logout")
    public String logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return ("login");
    }
    @GetMapping("/testing")
    public String goTest(Model model)
    {
        testServiceJAVA.addQuestions();
        model.addAttribute("count", testServiceJAVA.getCountAnswer());
        model.addAttribute("testSize", testServiceJAVA.getSizeOfTest());
        model.addAttribute("question", testServiceJAVA.getQuestion());
        model.addAttribute("var1", testServiceJAVA.getAnswers().get(0).answer);
        model.addAttribute("var2", testServiceJAVA.getAnswers().get(1).answer);
        model.addAttribute("var3", testServiceJAVA.getAnswers().get(2).answer);
        model.addAttribute("var4", testServiceJAVA.getAnswers().get(3).answer);
        return "testing";

    }

    @PostMapping ("/checkAnswer")
    public String checkAnwer(@RequestParam Map allParams, Model model){
        Object qwest = null;
        for(Object s:allParams.keySet()){
            qwest= s;
        }
        testServiceJAVA.chetBalls((String)qwest);
        if(testServiceJAVA.stopTest()==0){
            model.addAttribute("result", testServiceJAVA.getProcentCoot());
            return "resultTest";
        }
        System.out.println("usuus "+testServiceJAVA.getCountAnswer());
        model.addAttribute("count", testServiceJAVA.getCountAnswer());
        model.addAttribute("question", testServiceJAVA.getQuestion());
        model.addAttribute("testSize", testServiceJAVA.getSizeOfTest());
        model.addAttribute("var1", testServiceJAVA.getAnswers().get(0).answer);
        model.addAttribute("var2", testServiceJAVA.getAnswers().get(1).answer);
        model.addAttribute("var3", testServiceJAVA.getAnswers().get(2).answer);
        model.addAttribute("var4", testServiceJAVA.getAnswers().get(3).answer);
        return "testing";
    }

}
