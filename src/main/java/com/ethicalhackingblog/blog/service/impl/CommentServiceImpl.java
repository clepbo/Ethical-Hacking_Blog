package com.ethicalhackingblog.blog.service.impl;

import com.ethicalhackingblog.blog.model.Comment;
import com.ethicalhackingblog.blog.repository.CommentRepository;
import com.ethicalhackingblog.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repo;

    @Override
    public Comment saveComment(Comment comment) {
        return repo.save(comment);
    }

    @Override
    public List<Comment> getAllComment(Comment comment) {
        return repo.findAll();
    }

    @Override
    public Comment getCommentById(long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException());
    }
}
