package com.akash.employeemanagementsystem.controller;

import com.akash.employeemanagementsystem.auth_entity.AuthenticateRequest;
import com.akash.employeemanagementsystem.auth_entity.AuthenticateResponse;
import com.akash.employeemanagementsystem.auth_entity.RegisterRequest;
import com.akash.employeemanagementsystem.auth_service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticateResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok().body(authenticationService.registerUser(registerRequest));
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest registerRequest){
        return ResponseEntity.ok().body(authenticationService.authenticateUser(registerRequest));
    }
}
