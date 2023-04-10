package com.spring.jwt.spring_security.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException{

    private String code;
    private String message;
}
