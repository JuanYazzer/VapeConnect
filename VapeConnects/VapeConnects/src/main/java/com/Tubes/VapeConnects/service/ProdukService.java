package com.Tubes.VapeConnects.service;

import com.Tubes.VapeConnects.model.Produk;
import com.Tubes.VapeConnects.repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdukService {

    private final ProdukRepository ProdukRepository;

    @Autowired
    public ProdukService(ProdukRepository ProdukRepository) {
        this.ProdukRepository = ProdukRepository;
    }

    public List<Produk> getAllProduk() {
        return ProdukRepository.findAll();
    }

    public Produk getProdukById(Integer id) {
        Optional<Produk> produk = ProdukRepository.findById(id);
        return produk.orElseThrow(() -> new RuntimeException("Produk tidak ditemukan"));
    }

    public Produk saveProduk(Produk produk) {
        return ProdukRepository.save(produk);
    }

    public void deleteProduk(Integer id) {
        ProdukRepository.deleteById(id);
    }
}