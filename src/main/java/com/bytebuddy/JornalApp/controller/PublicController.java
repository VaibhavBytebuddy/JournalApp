package com.bytebuddy.JornalApp.controller;

import com.bytebuddy.JornalApp.entity.User;
import com.bytebuddy.JornalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private UserService userService;


    @PostMapping("/create-user")
    ////localhost:8080/public/create-user :-----------------POST
    public void createUser(@RequestBody User user)
    {
        userService.saveEntry(user);

    }

    @GetMapping("health-check")
    public String healthCheck()
    {
        return "OK";
    }
}
