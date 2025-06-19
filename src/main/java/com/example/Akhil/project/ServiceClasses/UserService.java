package com.example.Akhil.project.ServiceClasses;

import com.example.Akhil.project.DTOClasses.UserDTO;

public interface UserService {
    UserDTO addUser(UserDTO userDTO);
    UserDTO getUser(Integer user_id);
    UserDTO updateUser(Integer user_id, UserDTO userDTO);
    String deleteUser(Integer user_id);

}
