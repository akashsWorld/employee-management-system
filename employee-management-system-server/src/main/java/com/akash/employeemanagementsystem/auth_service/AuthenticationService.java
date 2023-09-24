package com.akash.employeemanagementsystem.auth_service;

import com.akash.employeemanagementsystem.auth_entity.*;
import com.akash.employeemanagementsystem.repository.EmployeeUserRepository;
import com.akash.employeemanagementsystem.repository.TokenRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final EmployeeUserRepository employeeUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepository;




    public AuthenticateResponse registerUser(RegisterRequest registerRequest) {
        EmployeeUser employeeUser = EmployeeUser.builder()
                .userPassword(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(Role.ADMIN)
                .build();
        employeeUser = employeeUserRepository.save(employeeUser);

        TokenEntity tokenEntity = TokenEntity.builder()
                .token(jwtService.generateToken(employeeUser))
                .employeeUser(employeeUser)
                .createdAt(new Date(System.currentTimeMillis()))
                .build();

        tokenEntity = tokenRepository.save(tokenEntity);

        System.out.println("The Token entity is saved");

        return AuthenticateResponse.builder().token(tokenEntity.getToken()).build();
    }

    public AuthenticateResponse authenticateUser(AuthenticateRequest registerRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerRequest.getEmail(),
                        registerRequest.getPassword()
                )
        );

        EmployeeUser employeeUser =
                employeeUserRepository.findEmployeeUserByEmail(registerRequest.getEmail()).orElseThrow(RuntimeException::new);

        TokenEntity  tokenEntity = employeeUser.getToken();

        if(!jwtService.isTokenValid(employeeUser.getToken().getToken(),employeeUser)){

            tokenEntity =
                    tokenRepository.findById(employeeUser.getEmployeeId())
                            .orElseThrow(()->new RuntimeException("Token Not Found"));

//            Create New Token
            tokenEntity= TokenEntity.builder()
                    .createdAt(new Date(System.currentTimeMillis()))
                    .token(jwtService.generateToken(employeeUser))
                    .build();


            return AuthenticateResponse.builder()
                    .token(tokenRepository.save(tokenEntity).getToken())
                    .build();
        };


        return AuthenticateResponse.builder().token(tokenEntity.getToken()).build();
    }
}
