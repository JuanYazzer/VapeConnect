package com.Tubes.VapeConnects.repository;

import com.Tubes.VapeConnects.model.Produk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdukRepository extends JpaRepository<Produk, Integer> {
    
    // Cari produk dengan stok kurang dari nilai tertentu
    List<Produk> findByStockLessThan(int stock);
    
    // Ambil 3 produk dengan stok terkecil
    List<Produk> findTop3ByOrderByStockAsc();
}