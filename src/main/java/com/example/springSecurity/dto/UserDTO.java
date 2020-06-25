package com.example.springSecurity.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class UserDTO implements UserDetails {

    private Integer id;
    private Set<? extends GrantedAuthority> grantedAuthorities;
    private String password;
    private String username;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public UserDTO(Integer id, Set<? extends GrantedAuthority> grantedAuthorities, String password, String username, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.id = id;
        this.grantedAuthorities = grantedAuthorities;
        this.password = password;
        this.username = username;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return isAccountNonExpired() == userDTO.isAccountNonExpired() &&
                isAccountNonLocked() == userDTO.isAccountNonLocked() &&
                isCredentialsNonExpired() == userDTO.isCredentialsNonExpired() &&
                isEnabled() == userDTO.isEnabled() &&
                grantedAuthorities.equals(userDTO.grantedAuthorities) &&
                getPassword().equals(userDTO.getPassword()) &&
                getUsername().equals(userDTO.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(grantedAuthorities, getPassword(), getUsername(), isAccountNonExpired(), isAccountNonLocked(), isCredentialsNonExpired(), isEnabled());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                "grantedAuthorities=" + grantedAuthorities +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", isAccountNonExpired=" + isAccountNonExpired +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
