package com.akash.employeemanagementsystem.auth_config;

import com.akash.employeemanagementsystem.auth_service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//This class is used to authenticate each requests every time a request came with the help of jwt token.
@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if(authHeader==null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request,response);
                return;
        }

        jwt = authHeader.substring(7);
        userEmail= jwtService.extractUserEmail(jwt);


//        The Second condition checks whether a user is authenticated or not. if its null that means user is not
//        authenticated.
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

//            Check whether the token is valid or not.
            if(jwtService.isTokenValid(jwt,userDetails)){
//                To Update the security context holder UsernamePasswordAuthenticationToken object is needed.
                UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(userDetails,
                        null,userDetails.getAuthorities());

//                Set the details of the request in to the auth token.
                authtoken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
//                Update the SecurityContextHolder with the auth token.
                SecurityContextHolder.getContext().setAuthentication(authtoken);

            }

        }

//        if the user is not authenticated for jwt expiration or any reason then forward request and response to the
//        next security filter.

        filterChain.doFilter(request,response);

    }
}
