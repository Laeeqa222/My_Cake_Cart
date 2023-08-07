package com.Laeeq.major.controller;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import com.Laeeq.major.global.GlobalData;
import com.Laeeq.major.model.Role;
import com.Laeeq.major.model.User;
import com.Laeeq.major.repository.RoleRepository;
import com.Laeeq.major.repository.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

    @Autowired
     private BCryptPasswordEncoder bCryptPasswordEncoder;
     @Autowired
     UserRepository userRepository;
     @Autowired
     RoleRepository roleRepository;
     @GetMapping("/login")
     public String Login() {
         GlobalData.isLoggedin=false;
         return "login";
     }
     @GetMapping("/register")
     public String Register() {
         return "register";
     }
     @PostMapping("/register")
     public String postRegister(@ModelAttribute("user") User user,HttpServletRequest request)throws ServletException{
        String password =user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles=new ArrayList<>();
        roles.add(roleRepository.findById(2).get());
        user.setRoles(roles);
        userRepository.save(user);
        request.login(user.getEmail(), password);
         return "redirect:/";
     }
     @GetMapping("/error")
     public String  error() {
         return "404";
     }
}
