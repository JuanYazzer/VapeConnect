package com.Tubes.VapeConnects.repository;

import com.Tubes.VapeConnects.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Tubes.VapeConnects.model.User;
import com.Tubes.VapeConnects.model.Produk;
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByUserAndProduct(User user, Produk product);
}
