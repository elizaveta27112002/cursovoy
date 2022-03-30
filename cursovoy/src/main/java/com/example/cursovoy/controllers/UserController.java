package com.example.cursovoy.controllers;

import com.example.cursovoy.models.Role;
import com.example.cursovoy.models.User;
import com.example.cursovoy.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }
    @GetMapping("/{id}/edit")
    public String adminEdit(@PathVariable(value = "id") long id, Model model){
        Optional<User> user = userRepo.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @PostMapping
    public String userSave(@RequestParam String username, @RequestParam(name="roles[]",required = false) String[] roles,
                           @RequestParam("userId") User user){
        user.setUsername(username);
        user.getRoles().clear();
//        System.out.println("Check");
        if(roles!=null)
        {
            Arrays.stream(roles).forEach(r->user.getRoles().add(Role.valueOf(r)));
//            System.out.println(roles);
        }
        userRepo.save(user);

        return "redirect:/admin";
    }

}
