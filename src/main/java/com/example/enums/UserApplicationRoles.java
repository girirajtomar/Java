package com.example.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.enums.UserApplicationPermissions.*;

public enum UserApplicationRoles {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE)),
    TRAINEEADMIN(Sets.newHashSet(COURSE_READ));

    private final Set<UserApplicationPermissions> permission;
    UserApplicationRoles(Set<UserApplicationPermissions> permission){
        this.permission = permission;
    }

    public Set<UserApplicationPermissions> getPermissions(){
        return this.permission;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> grantedAuthorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return grantedAuthorities;
    }
}
