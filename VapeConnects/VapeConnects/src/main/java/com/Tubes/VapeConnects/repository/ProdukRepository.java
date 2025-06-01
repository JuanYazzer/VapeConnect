package com.Tubes.VapeConnects.repository;

import com.Tubes.VapeConnects.model.Produk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdukRepository extends JpaRepository<Produk, Integer> {
    
    List<Produk> findByStockLessThan(int stock);
    
    List<Produk> findTop3ByOrderByStockAsc();
    
    // Tambahkan method untuk mencari semua produk
    List<Produk> findAll();
    
    // Tambahkan method untuk mencari produk berdasarkan ID
    Optional<Produk> findById(Integer id);
    
    // Tambahkan method untuk menghapus produk
    void deleteById(Integer id);
}