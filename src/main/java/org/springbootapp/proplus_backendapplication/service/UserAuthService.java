package org.springbootapp.proplus_backendapplication.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.constants.ApplicationConstants;
import org.springbootapp.proplus_backendapplication.model.User;
import org.springbootapp.proplus_backendapplication.repository.UserRepository;
import org.springbootapp.proplus_backendapplication.dto.response.JwtDto;
import org.springbootapp.proplus_backendapplication.dto.response.MessageDto;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserRepository userRepository;
    private final ValidationService validationService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final Environment environment;

    public MessageDto register(User user) {
        validationService.checkUsernameExists(user.getUsername());
        validationService.checkEmailExists(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new MessageDto("Registration successful! You can now log in using your credentials.");
    }

    public JwtDto login(String username, String password) {
        String jwt = "";
        Authentication auth = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        Authentication authenticationResult = authenticationManager.authenticate(auth);
        if (authenticationResult != null && authenticationResult.isAuthenticated() && environment != null) {
            String secret = environment.getProperty(ApplicationConstants.JWT_SECRET_KEY, ApplicationConstants.JWT_SECRET_KEY_DEFAULT_VALUE);
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            jwt = Jwts.builder().issuer("ProPlus")
                    .subject("JWT Token")
                    .claim("username", authenticationResult.getName())
                    .issuedAt(new Date())
                    .expiration(new Date(new Date().getTime() + 30000000))
                    .signWith(secretKey).compact();
        }
        return new JwtDto(jwt);
    }
}
