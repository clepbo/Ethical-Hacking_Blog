package com.ethicalhackingblog.user.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="username", nullable = false)
    private String username;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="passowrd", nullable = false)
    private String passowrd;
    @Column(name="role", nullable = false)
    private String role;
}