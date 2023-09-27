package com.akash.employeemanagementsystem.auth_entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.akash.employeemanagementsystem.auth_entity.Authorities.ADMIN_CREATE;
import static com.akash.employeemanagementsystem.auth_entity.Authorities.ADMIN_READ;
import static com.akash.employeemanagementsystem.auth_entity.Authorities.ADMIN_DELETE;
import static com.akash.employeemanagementsystem.auth_entity.Authorities.ADMIN_UPDATE;
import static com.akash.employeemanagementsystem.auth_entity.Authorities.MANAGER_CREATE;
import static com.akash.employeemanagementsystem.auth_entity.Authorities.MANAGER_READ;
import static com.akash.employeemanagementsystem.auth_entity.Authorities.MANAGER_DELETE;
import static com.akash.employeemanagementsystem.auth_entity.Authorities.MANAGER_UPDATE;
import static com.akash.employeemanagementsystem.auth_entity.Authorities.ADMIN_CREATOR;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {

    USER(
            Set.of(

            )
    ),

    SYSTEM_ADMIN(
            Set.of(
                    ADMIN_CREATOR,
                    ADMIN_READ,
                    ADMIN_CREATE,
                    ADMIN_DELETE,
                    ADMIN_UPDATE,
                    MANAGER_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE
            )
    ),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    MANAGER_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE
            )
    )
    ,MANAGER(
            Set.of(
                    MANAGER_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE
            )
    );


    @Getter
    private final Set<Authorities> authorities;


    public List<SimpleGrantedAuthority> getAllAuthorities(){

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = getAuthorities().stream().map(
                permissions-> new SimpleGrantedAuthority(permissions.getPermission())
        ).collect(Collectors.toList());

        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return simpleGrantedAuthorities;
    }


}
