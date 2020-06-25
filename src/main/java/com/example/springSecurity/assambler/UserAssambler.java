package com.example.springSecurity.assambler;

import com.example.springSecurity.dto.UserDTO;
import com.example.springSecurity.entity.User;
import com.example.springSecurity.security.ApplicationUserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class UserAssambler {
    public User toEntity(UserDTO userDTO){
        return new User(
                userDTO.getAuthorities().toArray()[0].toString(),
                userDTO.getPassword(),
                userDTO.getUsername(),
                userDTO.isAccountNonExpired(),
                userDTO.isAccountNonLocked(),
                userDTO.isCredentialsNonExpired(),
                userDTO.isEnabled());
    }
    public List<User> toEntity(List<UserDTO> userDTOS){
        List<User> entity = new ArrayList<>();
        userDTOS.forEach(it->{
            entity.add(toEntity(it));
        });
        return entity;
    }
    public UserDTO toDto(User user){

        return new UserDTO(user.getId(),
                ApplicationUserRole.valueOf(user.getGrantedAuthorities()).getGrantedAuthority(),
                user.getPassword(),
                user.getUsername(),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialsNonExpired(),
                user.isEnabled());
    }
    public List<UserDTO> toDto(List<User> user){
        List<UserDTO> dto = new ArrayList<>();
        user.forEach(it ->{
            dto.add(toDto(it));
        });
        return dto;
    }
}
