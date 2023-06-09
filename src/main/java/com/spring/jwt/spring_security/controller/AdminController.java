package com.spring.jwt.spring_security.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/index")
    public ResponseEntity<String>index(Principal principal){
        return ResponseEntity.ok("Welcome to admin panel : " + principal.getName());
    }
}
