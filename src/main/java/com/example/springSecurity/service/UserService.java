package com.example.springSecurity.service;

import com.example.springSecurity.assambler.UserAssambler;
import com.example.springSecurity.dto.UserDTO;
import com.example.springSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAssambler userAssambler;

    public UserDTO findByUsername(String username){
        if(!userRepository.findByUsername(username).isPresent()) throw new UsernameNotFoundException("Nie znaleziono użytkownika o podanym loginie: "+username);
        return userAssambler.toDto(userRepository.findByUsername(username).get());
    }
}
