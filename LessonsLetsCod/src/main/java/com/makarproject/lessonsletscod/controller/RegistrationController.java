package com.makarproject.lessonsletscod.controller;

import com.makarproject.lessonsletscod.entity.Role;
import com.makarproject.lessonsletscod.entity.User;
import com.makarproject.lessonsletscod.repositories.UserRepo;
import com.makarproject.lessonsletscod.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

//    @Autowired
//    private MailSender mailSender;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration(User user) {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {

        User userfromDb = userRepo.findByUsername(user.getUsername());
        if (userfromDb != null) {
            return "registration";
        }
        if (bindingResult.hasErrors() == true) {
            return "registration";
        }
        User emailUser = userRepo.findUsersByEmail(user.getEmail());
        if (emailUser != null) {
            model.addAttribute("erroreEM", new Error("Email is Buisy"));
            return "registration";
        }
        if (bindingResult.hasErrors() == false) {
        boolean check = userService.passwordValidation(user.getPassword(), user.getConfimPassword());
            if(check==false){
                model.addAttribute("errorePasw", "You must use 1 simbol (a-z),(A-Z),(0-9), size(7-20)");
                return "registration";
            }

            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            user.setEmail(user.getEmail());
            user.setActivationCode(UUID.randomUUID().toString());
            if (!Objects.equals(user.getPassword(), user.getConfimPassword())) {
                model.addAttribute("Errore", "Passwords do not match");
                model.addAttribute(new User());
                return "registration";
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setConfimPassword(passwordEncoder.encode(user.getConfimPassword()));//шифруем наши пароли на этапе создания пользователя
            userRepo.save(user);
            return "login";
        }
        return "redirect:/login";
    }
}
