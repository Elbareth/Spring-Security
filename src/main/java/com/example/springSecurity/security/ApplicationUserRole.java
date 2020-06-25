package com.example.springSecurity.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ApplicationUserPremission.COURSE_READ,
            ApplicationUserPremission.COURSE_WRITE,
            ApplicationUserPremission.STUDENT_READ,
            ApplicationUserPremission.STUDENT_WRITE)),
    TRAINEE(Sets.newHashSet(ApplicationUserPremission.COURSE_READ,
        ApplicationUserPremission.STUDENT_READ));


    private final Set<ApplicationUserPremission> permission;

    ApplicationUserRole(Set<ApplicationUserPremission> permission) {
        this.permission = permission;
    }

    public Set<ApplicationUserPremission> getPermission() {
        return permission;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permission = getPermission().stream()
                .map(it -> new SimpleGrantedAuthority(it.getPermission()))
                .collect(Collectors.toSet());
        permission.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permission;
    }
}
