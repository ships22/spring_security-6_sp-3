package com.spring.jwt.spring_security.service.security;

import com.spring.jwt.spring_security.entity.User;
import com.spring.jwt.spring_security.exception.BaseException;
import com.spring.jwt.spring_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;

import java.util.stream.Collectors;


public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUserDetails userDetails = getUserDetails(username);
        if (ObjectUtils.isEmpty(userDetails)){
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Invalid username or password");
        }
        return userDetails;
    }

    private AppUserDetails getUserDetails(String username){
        User user = userRepository.findByUsername(username);

        if (ObjectUtils.isEmpty(user)){
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Invalid username or password.");
        }

        return new AppUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList())
        );
    }
}
