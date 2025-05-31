package com.Tubes.VapeConnects.service;

import com.Tubes.VapeConnects.model.Review;
import com.Tubes.VapeConnects.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    
    public Review saveReview(Review review) {
        review.setCreatedAt(new Date());
        return reviewRepository.save(review);
    }
}