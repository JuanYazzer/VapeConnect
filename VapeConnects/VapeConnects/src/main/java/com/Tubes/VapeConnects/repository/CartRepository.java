package com.Tubes.VapeConnects.repository;

import com.Tubes.VapeConnects.model.Cart;
import com.Tubes.VapeConnects.model.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser(User user);
}
