package com.Tubes.VapeConnects.repository;

import com.Tubes.VapeConnects.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Method untuk menghitung jumlah user
    long count();
    User findByUsername(String username);
}