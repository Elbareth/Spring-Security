package com.example.springSecurity.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //Sprawdza czy dane podane przez użytkowika są prawidłowe
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernameAndPasswordAuthenticationRequest usernameAndPasswordRequest =
                    new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);// rzutujemy request na nasz obiekt
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usernameAndPasswordRequest.getUsername(),//to sa zasady
                    usernameAndPasswordRequest.getPassword()
            ));
            return authenticate;//ta metoda sprawdza czy login istnieje a jeśli tak to czy haslo jej poprawne
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //służy do wysyłania tokenów do użytkownika
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String key = "secure123456789101112131415161718192021222324252627282930";//tego nikt nie ma odgadnąć i mabyc długie
        String token = Jwts.builder()
                .setSubject(authResult.getName())//login użytkownika
                .claim("authorities", authResult.getAuthorities())//body
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2))) //ile ważny jest token
                .signWith(SignatureAlgorithm.HS256, Keys.hmacShaKeyFor(key.getBytes()))//wysyłanie klucza wraz z algorytmem
                .compact();
        response.addHeader("Authorization", "Bearer "+token);//wysłanie tokena do uzytkownika
    }
}
