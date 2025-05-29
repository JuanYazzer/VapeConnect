package com.Tubes.VapeConnects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Tubes.VapeConnects.model.Produk;

public interface ProdukRepository extends JpaRepository<Produk, Integer> {
    
}
