package com.ethicalhackingblog.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @GetMapping("/login")
    public String login(Authentication authentication, Model model) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        if (role.equals("ROLE_ADMIN")) {
            // Perform actions for admin role
            return "admin-dashbvoard";
        }
        return "login";
    }
}
