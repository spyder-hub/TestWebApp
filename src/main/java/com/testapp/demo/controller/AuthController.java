package com.testapp.demo.controller;


import com.testapp.demo.entity.User;
import com.testapp.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {


    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/change-password")
    public String changePasswordPage() {
        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam String username,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {

        User user = userService.findByUsername(username);

        if (user != null && user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            userService.registerUser(user);
        }

        return "redirect:/home";
    }
}
