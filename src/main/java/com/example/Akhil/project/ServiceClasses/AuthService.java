package com.example.Akhil.project.ServiceClasses;

import com.example.Akhil.project.DTOClasses.AuthResponseDTO;
import com.example.Akhil.project.DTOClasses.LoginDTO;
import com.example.Akhil.project.DTOClasses.RegisterDTO;

public interface AuthService {
    AuthResponseDTO register(RegisterDTO registerDTO);
    AuthResponseDTO login(LoginDTO loginDTO);
}
