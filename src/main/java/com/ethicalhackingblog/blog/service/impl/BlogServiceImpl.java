package com.ethicalhackingblog.blog.service.impl;

import com.ethicalhackingblog.blog.exception.BlogNotFoundException;
import com.ethicalhackingblog.blog.model.Blog;
import com.ethicalhackingblog.blog.repository.BlogRepository;
import com.ethicalhackingblog.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;

    private final ResourceLoader resourceLoader;
    @Override
    public Blog saveBlog(Blog blog, MultipartFile blogPicture, MultipartFile blogEbook) throws IOException {
            if (!blogPicture.isEmpty()) {
                byte[] pictureBytes = blogPicture.getBytes();
                log.info("Uploading... {}", pictureBytes);
                //System.out.println(pictureBytes);
                blog.setBlogPicture(pictureBytes);
            }
            if (!blogEbook.isEmpty()) {
                byte[] ebookBytes = blogEbook.getBytes();
                log.info("Uploading... {}", ebookBytes);
                //System.out.println(ebookBytes);
                blog.setBlogEbook(ebookBytes);
            }
            return blogRepository.save(blog);

    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog editBlog(long id) throws BlogNotFoundException{
        Optional<Blog> findBlog = blogRepository.findById(id);
        if(findBlog.isPresent()){
            return findBlog.get();
        }
        throw new BlogNotFoundException("blog", "id", id);
    }

    @Override
    public void deleteBlog(long id) {
        blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException("blog", "id", id));
         blogRepository.deleteById(id);
    }

    @Override
    public void saveAllImagesToStaticFolder() {
        List<Blog> images = blogRepository.findAll();

        for (Blog image : images) {
            byte[] imageData = image.getBlogPicture();
            System.out.println(imageData);

            try {
                String fileName = image.getId()+".jpg";
                Path destinationFile = Paths.get("src/main/resources/static/blogImage/", fileName);
                Files.write(destinationFile, imageData);
            } catch (IOException e) {
            }
        }
    }

    @Override
    public Blog getBlogById(long id) {
        return blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException("blog", "id", id));
    }

}
