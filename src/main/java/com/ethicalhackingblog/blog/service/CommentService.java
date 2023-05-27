package com.ethicalhackingblog.blog.service;

import com.ethicalhackingblog.blog.model.Comment;

import java.util.List;

public interface CommentService {
    Comment saveComment (Comment comment);

    List<Comment> getAllComment (Comment comment);
    Comment getCommentById (long id);
}
