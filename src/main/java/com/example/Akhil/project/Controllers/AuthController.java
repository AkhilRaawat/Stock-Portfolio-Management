package com.example.Akhil.project.Controllers;

import com.example.Akhil.project.DTOClasses.AuthResponseDTO;
import com.example.Akhil.project.DTOClasses.LoginDTO;
import com.example.Akhil.project.DTOClasses.RegisterDTO;
import com.example.Akhil.project.ServiceClasses.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterDTO registerDTO) {
        try {
            if (registerDTO.getUsername() == null || registerDTO.getUsername().isEmpty() ||
                    registerDTO.getEmail() == null || registerDTO.getEmail().isEmpty() ||
                    registerDTO.getPassword() == null || registerDTO.getPassword().isEmpty()) {

                AuthResponseDTO response = new AuthResponseDTO();
                response.setMessage("All fields are required");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            AuthResponseDTO response = authService.register(registerDTO);
            if (response.getToken() != null) {
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            AuthResponseDTO response = new AuthResponseDTO();
            response.setMessage("Registration failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            if (loginDTO.getUsername() == null || loginDTO.getUsername().isEmpty() ||
                    loginDTO.getPassword() == null || loginDTO.getPassword().isEmpty()) {

                AuthResponseDTO response = new AuthResponseDTO();
                response.setMessage("Username and password are required");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            AuthResponseDTO response = authService.login(loginDTO);
            if (response.getToken() != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            AuthResponseDTO response = new AuthResponseDTO();
            response.setMessage("Login failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}