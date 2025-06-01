package com.Tubes.VapeConnects.service;

import com.Tubes.VapeConnects.model.Produk;
import com.Tubes.VapeConnects.repository.ProdukRepository;
import com.Tubes.VapeConnects.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final ProdukRepository produkRepository;
    private final UserRepository userRepository;

    @Autowired
    public DashboardService(ProdukRepository produkRepository, UserRepository userRepository) {
        this.produkRepository = produkRepository;
        this.userRepository = userRepository;
    }

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // 1. Total produk
        long totalProducts = produkRepository.count();
        stats.put("totalProducts", totalProducts);
        
        // 2. Produk dengan stok rendah (kurang dari 10)
        List<Produk> lowStockProducts = produkRepository.findByStockLessThan(10);
        stats.put("lowStockProducts", lowStockProducts.size());
        
        // 3. Produk terlaris (sementara: produk dengan stok terkecil)
        List<Produk> bestSellers = produkRepository.findTop3ByOrderByStockAsc();
        stats.put("bestSellers", bestSellers);
        
        // 4. Total pengguna
        long totalUsers = userRepository.count();
        stats.put("totalUsers", totalUsers);
        
        // 5. Daftar semua produk untuk manajemen produk
        List<Produk> allProducts = produkRepository.findAll();
        stats.put("allProducts", allProducts);
        
        // 6. Total pendapatan (dummy data untuk sementara)
        stats.put("totalRevenue", new BigDecimal("20000000"));
        
        return stats;
    }
}