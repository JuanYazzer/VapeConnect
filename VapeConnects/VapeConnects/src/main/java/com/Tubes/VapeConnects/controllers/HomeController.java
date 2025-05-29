package com.Tubes.VapeConnects.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.Tubes.VapeConnects.model.Produk;
import com.Tubes.VapeConnects.repository.ProdukRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private final ProdukRepository produkRepository;
    
    public HomeController(ProdukRepository produkRepository) {
        this.produkRepository = produkRepository;
    }

    @GetMapping("/produk")
    public String showProducts(Model model) {
        List<Produk> produkList = produkRepository.findAll();
        model.addAttribute("produkList", produkList);
        return "home/produk";
    }
    @GetMapping("/beranda")
    public String showBeranda() {
        return "home/beranda";
    }
    @GetMapping("/riwayat")
    public String showRiwayat() {
        return "home/riwayat";
    }
}
