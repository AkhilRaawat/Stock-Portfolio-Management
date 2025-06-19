package com.example.Akhil.project.DTOClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.Akhil.project.Tables.Portfolio;
import com.example.Akhil.project.Tables.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer user_id;
    private String username;
    private String email;
    private String password;
    
    @JsonIgnore
    private Timestamp createdAt;
    
    private Portfolio portfolio;
    private Transaction transaction;
}
