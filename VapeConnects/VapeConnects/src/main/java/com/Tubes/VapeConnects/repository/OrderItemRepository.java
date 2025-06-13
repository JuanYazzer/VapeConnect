package com.Tubes.VapeConnects.repository;

import com.Tubes.VapeConnects.model.OrderItem;
import com.Tubes.VapeConnects.model.Produk;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("SELECT SUM(oi.quantity) FROM OrderItem oi WHERE oi.product.id = :productId")
    Integer getTotalQuantityByProductId(@Param("productId") Long productId);

    @Query("SELECT DISTINCT oi.product FROM OrderItem oi WHERE oi.order.customer.id = :userId")
    List<Produk> findDistinctProductsByUserId(@Param("userId") Long userId);
}

