package com.ethicalhackingblog.user.service;

import com.ethicalhackingblog.user.exception.UserNotFoundException;
import com.ethicalhackingblog.user.model.User;
import com.ethicalhackingblog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceImpl implements UserService{

    private final UserRepository userRepository;
    @Override
    public User findUserById(long id) {
        return null;
    }

    @Override
    public UserDetails findUerByRole(String role) throws UserNotFoundException {
        User user = userRepository.findByRole(role);
        if (user == null) {
            throw new UserNotFoundException("user", "role", role);
        }
        return (UserDetails) user;
    }





}
