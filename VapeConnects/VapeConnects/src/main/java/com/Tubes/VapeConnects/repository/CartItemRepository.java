package com.Tubes.VapeConnects.repository;

import com.Tubes.VapeConnects.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    // CartItemRepository.java
    Optional<CartItem> findByCartIdAndProdukId(Integer cartId, Integer produkId);

}