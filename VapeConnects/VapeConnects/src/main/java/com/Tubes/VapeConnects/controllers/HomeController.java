package com.Tubes.VapeConnects.controllers;

import java.util.List;

import jakarta.servlet.http.HttpSession; 
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

    private final ProdukRepository produkRepository;

    @Autowired
    public HomeController(ProdukRepository produkRepository) {
        this.produkRepository = produkRepository;
    }

    @GetMapping("/produk")
    public String showProducts(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "Guest"; // default kalau belum login
        }
        List<Produk> produkList = produkRepository.findAll();

        model.addAttribute("username", username);
        model.addAttribute("produkList", produkList);

        return "home/produk";
    }

    @GetMapping("/home")
    public String showBeranda(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "Guest";
        }
        model.addAttribute("username", username);
        return "home/home";
    }

    @GetMapping("/riwayat")
    public String showRiwayat(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "Guest";
        }
        model.addAttribute("username", username);
        return "home/riwayat";
    }
}
