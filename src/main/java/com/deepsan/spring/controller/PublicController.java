package com.deepsan.spring.controller;

import com.deepsan.spring.entity.User;
import com.deepsan.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public  class PublicController {

  @Autowired
   private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok";
    }


    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }



}
