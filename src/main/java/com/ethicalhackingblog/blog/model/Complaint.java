package com.ethicalhackingblog.blog.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "complaint")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="email")
    private String email;
    @Column(name="complaint_text", columnDefinition = "LONGTEXT")
    private String complaint_text;
}
