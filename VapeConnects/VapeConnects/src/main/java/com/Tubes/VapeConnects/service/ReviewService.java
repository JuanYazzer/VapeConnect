package com.Tubes.VapeConnects.service;

import com.Tubes.VapeConnects.model.Produk;
import com.Tubes.VapeConnects.model.Review;
import com.Tubes.VapeConnects.model.User;
import com.Tubes.VapeConnects.repository.ReviewRepository;
import com.Tubes.VapeConnects.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProdukService produkService;

    public boolean hasUserReviewedProduct(String username, int productId) {
        User user = userRepository.findByUsername(username);
        Produk product = produkService.getProdukById(productId);
        return reviewRepository.existsByUserAndProduct(user, product);
    }

    public Review saveReview(Review review) {
        review.setCreatedAt(new Date());
        return reviewRepository.save(review);
    }
}
