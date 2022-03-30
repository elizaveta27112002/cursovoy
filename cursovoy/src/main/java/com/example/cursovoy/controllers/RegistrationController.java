package com.example.cursovoy.controllers;

import com.example.cursovoy.models.Role;
import com.example.cursovoy.models.User;
import com.example.cursovoy.repo.UserRepo;
import com.example.cursovoy.validationforminput.PersonForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration(PersonForm personForm){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid PersonForm personForm, BindingResult bindingResult, User user, Model model){
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null)
        {
            model.addAttribute("message", "Такой пользователь уже существует!!!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "redirect:/login";
    }
}

