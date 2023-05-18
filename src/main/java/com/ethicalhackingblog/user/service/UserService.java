package com.ethicalhackingblog.user.service;

import com.ethicalhackingblog.user.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User findUserById(long id);
    UserDetails findUerByRole(String role);
}
