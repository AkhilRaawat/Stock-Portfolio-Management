package com.example.Akhil.project.Tables;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "USER_SAPP")
@Data
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer user_id;
    private String username;
    private String email;
    private String password;
    
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
    
    @ManyToOne
    private Portfolio portfolio;
    @ManyToOne
    private Transaction transaction;
}
