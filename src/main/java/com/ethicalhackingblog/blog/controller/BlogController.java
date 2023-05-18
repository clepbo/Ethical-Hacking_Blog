package com.ethicalhackingblog.blog.controller;

import com.ethicalhackingblog.blog.exception.BlogNotFoundException;
import com.ethicalhackingblog.blog.model.Blog;
import com.ethicalhackingblog.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model){
        model.addAttribute("blog", new Blog());
        model.addAttribute("pageTitle", "Add New Blog");
        return "admin-dashboard";
    }

    @PostMapping("/dashboard/save")
    public String createBlog(RedirectAttributes redirectAttributes, @ModelAttribute("blog") Blog blog,
                             @RequestParam("blogPicture") MultipartFile blogPicture,
                             @RequestParam("blogEbook") MultipartFile blogEbook) throws IOException {

            blogService.saveBlog(blog, blogPicture, blogEbook);
            redirectAttributes.addFlashAttribute("message", "Blog has been uploaded");
            return "redirect:/dashboard/manage-blog";
    }

    @GetMapping("/dashboard/manage-blog")
    public String getAllBlogs(Model model){
        List<Blog> allBlogs = blogService.getAllBlogs();
        model.addAttribute("blog", allBlogs);
        return "manage-blogs";
    }

    @GetMapping("/dashboard/manage-blog/edit/{id}")
    public String editBlog(@PathVariable("id") long id,  Model model, RedirectAttributes ra){
        try{
            Blog editedBlog = blogService.editBlog(id);
            model.addAttribute("blog", editedBlog);
            model.addAttribute("pageTitle", "Edit Blog");
            ra.addFlashAttribute("message", "Blog has been updated");
            return "admin-dashboard";
        }catch (BlogNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/dashboard/manage-blog";
        }
    }

    @GetMapping("/dashboard/manage-blog/delete/{id}")
    public String deleteBlog(@PathVariable long id, RedirectAttributes ra) throws BlogNotFoundException{
        try{
            blogService.deleteBlog(id);
            ra.addFlashAttribute("message", "Blog has been deleted Successfully");
        }catch (BlogNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/dashboard/manage-blog";
    }

    @GetMapping("/blog")
    public String blogPage(Model model){
        blogService.saveAllImagesToStaticFolder();
        List<Blog> allBlogs = blogService.getAllBlogs();
        model.addAttribute("blog", allBlogs);
        return "index";
    }

    @PostMapping("/dashboard/blog/{id}")
    public String getBlogById(@PathVariable("id") long id, Model model){
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return "post-page";
    }
}
