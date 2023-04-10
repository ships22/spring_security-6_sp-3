package com.spring.jwt.spring_security.controller;

import com.spring.jwt.spring_security.dto.BaseResponseDTO;
import com.spring.jwt.spring_security.dto.UserDTO;
import com.spring.jwt.spring_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponseDTO>register(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.register(userDTO));
    }
}
