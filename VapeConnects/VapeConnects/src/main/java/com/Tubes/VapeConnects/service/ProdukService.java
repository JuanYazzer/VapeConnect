package com.Tubes.VapeConnects.service;

import com.Tubes.VapeConnects.model.Produk;
import com.Tubes.VapeConnects.repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdukService {
    @Autowired
    private ProdukRepository produkRepository;  // Nama variabel konsisten dengan tipe
    
    public List<Produk> getAllProduk() {
        return produkRepository.findAll();  // Menggunakan variabel yang benar
    }
    
    public Optional<Produk> getProdukById(Integer id) {  // Menggunakan Integer bukan long
        return produkRepository.findById(id);
    }
    
    // Tambahkan method lain sesuai kebutuhan
    public Produk saveProduk(Produk produk) {
        return produkRepository.save(produk);
    }
    
    public void deleteProduk(Integer id) {
        produkRepository.deleteById(id);
    }
}