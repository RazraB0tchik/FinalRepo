package com.makarproject.lessonsletscod.controller;

import com.makarproject.lessonsletscod.entity.User;
import com.makarproject.lessonsletscod.repositories.UserRepo;
import com.makarproject.lessonsletscod.services.MailSender;
import com.makarproject.lessonsletscod.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.Objects;

@Controller
@SessionAttributes({AvtorizeController.ATTRIBUTE_NAME})
public class AvtorizeController {
    static final String ATTRIBUTE_NAME = "email";

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @Autowired
    MailSender mailSender;


@InitBinder
void allowFields(WebDataBinder webDataBinder){
    webDataBinder.setAllowedFields("password");
}
    @GetMapping("/active")
    public String goCheck(String email){
        User user = userRepo.findUsersByEmail(email);
        System.out.println();
        if(user!=null){
            if(!StringUtils.isEmpty(email)){ //делаем рассылку сообщения
                String message = String.format(
                "Hello, go to link that activate your email!: http://localhost:8080/activateOK/%s",
                        user.getActivationCode()
                        );
                mailSender.send(email, "Activation code", message);
            }
            return "/login";
        }
        return "redirect:/registration";
    }

    @GetMapping("/activateOK/{code}")
        public String activate(Model model, @PathVariable String code){
        User user = userService.finByCode(code);
        model.addAttribute(ATTRIBUTE_NAME, user.getEmail());
        boolean isActivate = userService.activateUser(code);
        if (isActivate){
            model.addAttribute(new User());
            model.addAttribute("userEmail", "Hello " + user.getEmail()+"!");
            return "OKAunt";
        }
        else{
            return "registration";
        }
}
@PostMapping ("/writePas")
    public String rewritePass(@ModelAttribute(ATTRIBUTE_NAME) String email, String password, String confimPassword, SessionStatus sessionStatus, Model model){
    model.addAttribute("userEmail", "Hello " + email +"!");
    if(!Objects.equals(password, confimPassword)){
        model.addAttribute("Errore", "Passwords do not match");
        model.addAttribute(new User());
        return "OKAunt";
    }
    boolean check = userService.passwordValidation(password, confimPassword);
    if(check==false){
        model.addAttribute("errorePasw", "You must use 1 simbol (a-z),(A-Z),(0-9), size(7-20)");
        model.addAttribute(new User());
        return "OKAunt";
    }

    User user = (User) userService.findByEmail(email);
    user.setPassword(passwordEncoder.encode(password));
    user.setConfimPassword(passwordEncoder.encode(confimPassword));
    userRepo.save(user);
        return "login";
}
}
