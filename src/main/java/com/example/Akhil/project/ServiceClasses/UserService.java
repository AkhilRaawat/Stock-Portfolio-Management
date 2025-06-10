package com.stport.spm.ServiceClasses;

import com.stport.spm.DTOClasses.UserDTO;

public interface UserService {
    UserDTO addUser(UserDTO userDTO);
    UserDTO getUser(Integer user_id);
    UserDTO updateUser(Integer user_id, UserDTO userDTO);
    String deleteUser(Integer user_id);

}
