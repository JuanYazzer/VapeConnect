package com.Tubes.VapeConnects.repository;

import com.Tubes.VapeConnects.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Method untuk mengambil semua order berdasarkan ID customer
    List<Order> findByCustomerId(Long customerId);    

    // Menghitung SUM dari semua order tanpa memandang status
    @Query("SELECT SUM(o.total) FROM Order o")
    Double calculateTotalSales(); 
    
    // Asumsi Anda masih membutuhkan fungsi ini untuk perhitungan periodik (opsional)
    // @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.status = 'PAID' AND o.orderDate >= :startDate")
    // BigDecimal calculateSalesByDate(java.time.LocalDateTime startDate);
}
