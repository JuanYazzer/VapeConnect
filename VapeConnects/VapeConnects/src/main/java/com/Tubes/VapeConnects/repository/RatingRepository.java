package com.Tubes.VapeConnects.repository;

import com.Tubes.VapeConnects.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RatingRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveRating(Rating rating) {
        String sql = "INSERT INTO ratings (product_id, user_id, rating_value) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, rating.getProductId(), rating.getUserId(), rating.getRatingValue());
    }

    public List<Rating> findByProductId(int productId) {
        String sql = "SELECT * FROM ratings WHERE product_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
        Rating rating = new Rating();
        rating.setId(rs.getInt("id"));
        rating.setProductId(rs.getInt("product_id"));
        rating.setUserId(rs.getInt("user_id"));
        rating.setRatingValue(rs.getInt("rating_value"));
        return rating;
    }, productId);

    }
}
