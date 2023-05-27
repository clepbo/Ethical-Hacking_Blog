package com.ethicalhackingblog.blog.controller;

import com.ethicalhackingblog.blog.exception.BlogNotFoundException;
import com.ethicalhackingblog.blog.model.Blog;
import com.ethicalhackingblog.blog.model.Comment;
import com.ethicalhackingblog.blog.model.Complaint;
import com.ethicalhackingblog.blog.service.BlogService;
import com.ethicalhackingblog.blog.service.CommentService;
import com.ethicalhackingblog.blog.service.ComplaintService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BlogController {

    private final BlogService blogService;
    private final CommentService commentService;
    private final ComplaintService complaintService;


    //GetMapping for login page
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    //GetMapping for admin dashboard
    @GetMapping("/dashboard")
    public String adminDashboard(Model model){
        model.addAttribute("blog", new Blog());
        model.addAttribute("pageTitle", "Add New Blog");
        return "admin-dashboard";
    }

    //PostMapping to save new blog
    @PostMapping("/dashboard/save")
    public String createBlog(RedirectAttributes redirectAttributes, @ModelAttribute("blog") Blog blog,
                             @RequestParam("blogPicture") MultipartFile blogPicture) throws IOException {

            blogService.saveBlog(blog, blogPicture);
            redirectAttributes.addFlashAttribute("message", "Blog has been uploaded");
            return "redirect:/dashboard/manage-blog";
    }

    //binder for multipart file
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
            throws ServletException {

        // Convert multipart object to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

    }

    //GetMapping to manage blogs, get all blogs
    @GetMapping("/dashboard/manage-blog")
    public String getAllBlogs(Model model){
        List<Blog> allBlogs = blogService.getAllBlogs();
        model.addAttribute("blog", allBlogs);
        return "manage-blogs";
    }

    //GetMApping for editing blog by Id
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

    //GetMapping for deleting blog by Id
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

    //GetMapping for the blogpage, index page
    @GetMapping("/")
    public String blogPage(Model model){
        blogService.saveAllImagesToStaticFolder();
        List<Blog> allBlogs = blogService.getAllBlogs();
        model.addAttribute("comment", new Comment());
        model.addAttribute("complaint", new Complaint());
        model.addAttribute("blogs", allBlogs);
        return "index";
    }


    //GetMapping to view each blog
    @GetMapping("/dashboard/blog/{id}")
    public String getBlogById(@PathVariable("id") long id, Model model){
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return "post-page";
    }

    //PostMapping to comment
    @PostMapping("/comment/save")
    public String saveComment(@ModelAttribute("comment") Comment commentSave, @RequestParam("email") String email,
                              @RequestParam("comment_text") String comment_text, RedirectAttributes redirectAttributes){
        commentSave.setEmail(email);
        commentSave.setComment_text(comment_text);

        commentService.saveComment(commentSave);
        redirectAttributes.addFlashAttribute("message", "Your comment has been sent successfully");
        return "redirect:/";
    }

    //PostMapping to complaint
    @PostMapping("/complaint/save")
    public String saveComplaint(@ModelAttribute("complaint")Complaint complaintSave, @RequestParam("email") String email,
                                @RequestParam("complaint_text") String complaint_text, RedirectAttributes redirectAttributes){

        complaintSave.setEmail(email);
        complaintSave.setComplaint_text(complaint_text);
        complaintService.saveCompliant(complaintSave);
        redirectAttributes.addFlashAttribute("message", "Your complaint has been sent successfully, we'll get back to you shortly.");
        return "redirect:/";
    }
}
