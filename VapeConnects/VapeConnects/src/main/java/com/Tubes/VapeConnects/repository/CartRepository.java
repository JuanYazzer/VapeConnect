package com.Tubes.VapeConnects.repository;

import com.Tubes.VapeConnects.model.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
