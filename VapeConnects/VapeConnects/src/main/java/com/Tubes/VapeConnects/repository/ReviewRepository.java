package com.Tubes.VapeConnects.repository;

import com.Tubes.VapeConnects.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}