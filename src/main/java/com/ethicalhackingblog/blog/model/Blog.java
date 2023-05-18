package com.ethicalhackingblog.blog.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="blog_title")
    private String blogTitle;

    @Column(name="blog_descritption")
    private String blogDescription;

    @Column(name="blog_content", columnDefinition = "LONGTEXT")
    private String blogContent;

    @Column(name="blog_author")
    private String blogAuthor;

    @Column(name="blog_category")
    private String blogCategory;

    @Column(name="blog_tag")
    private String blogTag;

    @Column(name="blog_picture", columnDefinition = "BLOB")
    @Lob
    private byte[] blogPicture;

    @Column(name="blog_ebook", columnDefinition = "BLOB")
    @Lob
    private byte[] blogEbook;

    @Column(name="date_posted")
    @CreationTimestamp
    private LocalDateTime datePosted;

}
