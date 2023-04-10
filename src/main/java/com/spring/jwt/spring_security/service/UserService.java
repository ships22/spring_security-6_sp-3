package com.spring.jwt.spring_security.service;

import com.spring.jwt.spring_security.dto.BaseResponseDTO;
import com.spring.jwt.spring_security.dto.UserDTO;

public interface UserService {
    BaseResponseDTO register(UserDTO userDTO);
}
