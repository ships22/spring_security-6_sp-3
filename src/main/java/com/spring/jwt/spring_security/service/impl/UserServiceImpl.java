package com.spring.jwt.spring_security.service.impl;

import com.spring.jwt.spring_security.dto.BaseResponseDTO;
import com.spring.jwt.spring_security.dto.UserDTO;
import com.spring.jwt.spring_security.entity.Role;
import com.spring.jwt.spring_security.entity.User;
import com.spring.jwt.spring_security.exception.BaseException;
import com.spring.jwt.spring_security.repository.RoleRepository;
import com.spring.jwt.spring_security.repository.UserRepository;
import com.spring.jwt.spring_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public BaseResponseDTO register(UserDTO userDTO) {
        BaseResponseDTO response = new BaseResponseDTO();
        validateAccount(userDTO);
        User user = toUserModel(userDTO);
        try{
            userRepository.save(user);
            response.setCode(String.valueOf(HttpStatus.OK.value()));
            response.setMessage("Account successfully created.");
            System.out.println("test here success...");
        }catch (Exception e){
            response.setCode(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
            response.setMessage("Service unavailable.");
        }
        return response;
    }
    private User toUserModel(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Set<Role> roles = new HashSet<>();
        System.out.println("test here : " + userDTO.getRole());
        roles.add(roleRepository.findByName(userDTO.getRole()));
        user.setRoles(roles);
        return user;
    }

    //validate null data -
    private void validateAccount(UserDTO userDTO){
        if (ObjectUtils.isEmpty(userDTO)){
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Data must not be empty.");
        }
        //check duplicate user -
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (!ObjectUtils.isEmpty(user)){
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Username already exists.");
        }
        //validate role -
        List<String>roles = roleRepository.findAll().stream().map(Role::getName).toList();
        System.out.println("test list print : " + roles.toString());
        if (!roles.contains(userDTO.getRole())){
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Invalid role.");
        }
    }
}
