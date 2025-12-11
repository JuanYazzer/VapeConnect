package com.Tubes.VapeConnects.service;

import com.Tubes.VapeConnects.model.Produk;
import com.Tubes.VapeConnects.repository.OrderItemRepository;
import com.Tubes.VapeConnects.repository.OrderItemRepository.ProductSaleStats;
import com.Tubes.VapeConnects.repository.OrderRepository;
import com.Tubes.VapeConnects.repository.ProdukRepository;
import com.Tubes.VapeConnects.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final ProdukRepository produkRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public DashboardService(ProdukRepository produkRepository, UserRepository userRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.produkRepository = produkRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public BigDecimal getTotalPenjualan() {
        // Memanggil query yang SUM semua totalPrice di Order Repository
        Double totalDouble = orderRepository.calculateTotalSales();
        
        // KUNCI PERBAIKAN: Jika hasilnya null (tabel order kosong), kembalikan 0.
        if (totalDouble == null) {
            return BigDecimal.ZERO;
        } 
        return new BigDecimal(totalDouble);
    }

    public List<ProductSaleStats> getTopSellingProducts() {
        // Menghitung tanggal 30 hari yang lalu sebagai batas awal
        LocalDateTime startDate = LocalDateTime.now().minusDays(30);
        
        // Memanggil query yang sudah diperbaiki di OrderItemRepository
        return orderItemRepository.findTopSellingProducts(startDate);
    }

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        // Tentukan batas waktu 30 hari terakhir
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        
        // 1. Total produk
        long totalProducts = produkRepository.count();
        stats.put("totalProducts", totalProducts);
        
        // 2. Produk dengan stok rendah (kurang dari 10)
        List<Produk> lowStockProducts = produkRepository.findByStockLessThan(10);
        stats.put("lowStockProducts", lowStockProducts.size());
        
        // 3. Produk terlaris (sementara: produk dengan stok terkecil)
        List<OrderItemRepository.ProductSaleStats> bestSellersStats = orderItemRepository.findTopSellingProducts(thirtyDaysAgo);
        stats.put("bestSellers", bestSellersStats);
        
        // 4. Total pengguna
        long totalUsers = userRepository.count();
        stats.put("totalUsers", totalUsers);
        
        // 5. Daftar semua produk untuk manajemen produk
        List<Produk> allProducts = produkRepository.findAll();
        stats.put("allProducts", allProducts);
        
        return stats;
    }
}