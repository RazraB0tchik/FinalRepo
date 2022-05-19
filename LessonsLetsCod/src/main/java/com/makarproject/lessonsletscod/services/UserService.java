package com.makarproject.lessonsletscod.services;


import com.makarproject.lessonsletscod.entity.Answers;
import com.makarproject.lessonsletscod.entity.User;
import com.makarproject.lessonsletscod.repositories.QwestionRepo;
import com.makarproject.lessonsletscod.repositories.UserRepo;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service

public class UserService implements UserDetailsService {

    private final UserRepo userRepository;

    public UserService(UserRepo userRepository, QwestionRepo qwestionRepo) {
        this.userRepository = userRepository;
        this.qwestionRepo = qwestionRepo;
    }

    @Autowired
    private final QwestionRepo qwestionRepo;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        System.out.println(user);

        if (user == null) {
            throw new UsernameNotFoundException("User not founded!");
        }
        return user;
    }

    public UserDetails findByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findUsersByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("We cant find email!");
        } else {
            return user;
        }
    }

    public User finByCode(String code){
        User user = userRepository.findByActivationCode(code);
        return user;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if(user == null){
            return false;
        }
        user.setActivationCode(UUID.randomUUID().toString());

        userRepository.save(user);
        return true;
    }

    public boolean passwordValidation(String password, String confimPassword){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{7,20}$");

        Matcher mat1 = pattern.matcher(password);
        Matcher mat2 = pattern.matcher(confimPassword); //валидация

        if((mat1.matches() == false)||(mat2.matches() == false)){
           return false;
        }
        return true;
    }
}

//    public boolean createUser(User user){
//        User usr = userRepository.findByUsername(user.getUsername());
//        if(usr != null){
//            return false;
//        }
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
//        String code = UUID.randomUUID().toString();
//        user.setPassword(user.getPassword());
//        userRepository.save(user);
//        return true;
//    }
//    public User findUserById(Long userId) {
//        Optional<User> userFromDb = userRepository.findById(userId);
//        return userFromDb.orElse(new User());
//    }
//
//    public List<User> allUsers() {
//        return userRepository.findAll();
//    }
//
//    public boolean saveUser(User user) {
//        User userFromDB = userRepository.findByUsername(user.getUsername());
//
//        if (userFromDB != null) {
//            return false;
//        }
//
//        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return true;
//    }
