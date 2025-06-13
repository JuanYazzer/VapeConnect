package com.Tubes.VapeConnects.repository;

import com.Tubes.VapeConnects.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
