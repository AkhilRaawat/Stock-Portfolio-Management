package com.example.Akhil.project.ServiceClasses;

import com.example.Akhil.project.DTOClasses.AuthResponseDTO;
import com.example.Akhil.project.DTOClasses.LoginDTO;
import com.example.Akhil.project.DTOClasses.RegisterDTO;
import com.example.Akhil.project.Tables.User;
import com.example.Akhil.project.Tables.UserRepo;
import com.example.Akhil.project.Utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    @Override
    public AuthResponseDTO register(RegisterDTO registerDTO) {
        AuthResponseDTO response = new AuthResponseDTO();

        try {
            // Check if username already exists
            Optional<User> existingUser = userRepo.findByUsername(registerDTO.getUsername());
            if (existingUser.isPresent()) {
                response.setMessage("Username already exists");
                return response;
            }

            // Check if email already exists
            Optional<User> existingEmail = userRepo.findByEmail(registerDTO.getEmail());
            if (existingEmail.isPresent()) {
                response.setMessage("Email already exists");
                return response;
            }

            // Create new user
            User user = new User();
            user.setUsername(registerDTO.getUsername());
            user.setEmail(registerDTO.getEmail());
            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

            User savedUser = userRepo.save(user);

            // Generate JWT token
            String token = jwtUtil.generateToken(savedUser.getUsername());

            response.setToken(token);
            response.setUsername(savedUser.getUsername());
            response.setMessage("User registered successfully");

        } catch (Exception e) {
            response.setMessage("Registration failed: " + e.getMessage());
        }

        return response;
    }

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        AuthResponseDTO response = new AuthResponseDTO();

        try {
            Optional<User> userOptional = userRepo.findByUsername(loginDTO.getUsername());

            if (userOptional.isEmpty()) {
                response.setMessage("Invalid username or password");
                return response;
            }

            User user = userOptional.get();

            if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                response.setMessage("Invalid username or password");
                return response;
            }

            // Generate JWT token
            String token = jwtUtil.generateToken(user.getUsername());

            response.setToken(token);
            response.setUsername(user.getUsername());
            response.setMessage("Login successful");

        } catch (Exception e) {
            response.setMessage("Login failed: " + e.getMessage());
        }

        return response;
    }
}