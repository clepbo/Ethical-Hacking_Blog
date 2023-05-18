package com.ethicalhackingblog.user.repository;

import com.ethicalhackingblog.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByRole(String role);
}
