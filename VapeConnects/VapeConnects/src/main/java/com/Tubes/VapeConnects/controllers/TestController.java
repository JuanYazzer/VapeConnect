package com.Tubes.VapeConnects.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Tubes.VapeConnects.repository.ProdukRepository;

@RestController
public class TestController {

    @Autowired
    private ProdukRepository produkRepository;

    @GetMapping("/test-db")
    public String testDbConnection() {
        long count = produkRepository.count();
        return "Jumlah produk di DB: " + count;
    }
}
