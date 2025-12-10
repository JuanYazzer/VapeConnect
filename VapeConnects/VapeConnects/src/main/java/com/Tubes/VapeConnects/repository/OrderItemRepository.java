package com.Tubes.VapeConnects.repository;

import com.Tubes.VapeConnects.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {    
}
