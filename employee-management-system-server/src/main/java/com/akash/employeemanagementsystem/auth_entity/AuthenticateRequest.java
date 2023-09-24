package com.akash.employeemanagementsystem.auth_entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticateRequest {

    private String email;
    private String password;
}
