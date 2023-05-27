package com.ethicalhackingblog.blog.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="email")
    private String email;
    @Column(name="comment_text", columnDefinition = "LONGTEXT")
    private String comment_text;
}
