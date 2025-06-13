package com.Tubes.VapeConnects.repository;

import com.Tubes.VapeConnects.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Method untuk mengambil semua order berdasarkan ID customer
    List<Order> findByCustomerId(Long customerId);    
}
