package com.Tubes.VapeConnects.service;

import com.Tubes.VapeConnects.model.Rating;
import com.Tubes.VapeConnects.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public void saveRating(int productId, int userId, int ratingValue) {
        Rating rating = new Rating();
        rating.setProductId(productId);
        rating.setUserId(userId);
        rating.setRatingValue(ratingValue);
        ratingRepository.saveRating(rating);
    }

    public List<Rating> getRatingsByProductId(int productId) {
        return ratingRepository.findByProductId(productId);
    }
}
