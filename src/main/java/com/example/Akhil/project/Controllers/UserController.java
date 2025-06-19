package com.example.Akhil.project.Controllers;
import com.example.Akhil.project.DTOClasses.UserDTO;
import com.example.Akhil.project.ServiceClasses.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        try {
            if (userDTO.getUser_id() == null || userDTO.getUser_id().toString().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO savedUser = userService.addUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{user_id}")
    public ResponseEntity<UserDTO> getUser(Integer user_id) {
        UserDTO userDTO = userService.getUser(user_id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping("{user_id}")
    public ResponseEntity<UserDTO> updateUser(Integer user_id, UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(user_id, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{user_id}")
    public ResponseEntity<String> deleteUser(Integer user_id) {
        String msg = userService.deleteUser(user_id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }


}
