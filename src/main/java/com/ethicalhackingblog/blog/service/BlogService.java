package com.ethicalhackingblog.blog.service;

import com.ethicalhackingblog.blog.model.Blog;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BlogService {
    Blog saveBlog (Blog blog, MultipartFile blogPicture, MultipartFile blogEbook)throws IOException;
    List<Blog> getAllBlogs();

    Blog editBlog(long id);

    public void deleteBlog(long id);
    public void saveAllImagesToStaticFolder();

    Blog getBlogById(long id);
}
