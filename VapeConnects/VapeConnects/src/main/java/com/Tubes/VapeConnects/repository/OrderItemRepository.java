package com.Tubes.VapeConnects.repository;

import com.Tubes.VapeConnects.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // Custom DTO/Interface untuk menyimpan Nama Produk, Jumlah Terjual, dan Total Pendapatan Produk
    public interface ProductSaleStats {
        String getNamaProduk();
        Long getTotalTerjual();
        Double getTotalPendapatan();
    }

   @Query(value = "SELECT p.name as namaProduk, " + 
                   "SUM(oi.quantity) as totalTerjual, " +
                   "SUM(oi.quantity * oi.price) as totalPendapatan " + 
                   "FROM OrderItem oi JOIN oi.order o JOIN oi.product p " + 
                   "WHERE o.orderDate >= :startDate " + // <-- FILTER STATUS DIHILANGKAN
                   "GROUP BY p.id, p.name " + 
                   "ORDER BY totalTerjual DESC, totalPendapatan DESC " +
                   "LIMIT 3")
    List<ProductSaleStats> findTopSellingProducts(@Param("startDate") java.time.LocalDateTime startDate);
}
