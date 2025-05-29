// package com.Tubes.VapeConnects.controllers;

// import com.Tubes.VapeConnects.model.*;
// import com.Tubes.VapeConnects.repository.CartRepository;
// import com.Tubes.VapeConnects.repository.ProdukRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.*;

// @Controller
// @RequestMapping("/keranjang")
// public class KeranjjangController {

//     @Autowired
//     private CartRepository cartRepo;

//     @Autowired
//     private ProdukRepository produkRepo;

//     @PostMapping("/tambah")
//     public String tambahKeKeranjang(@RequestParam int produkId) {
//         // Ambil produk dari database
//         Produk produk = produkRepo.findById(produkId)
//                 .orElseThrow(() -> new RuntimeException("Produk tidak ditemukan"));

//         // Buat cart dan item
//         Cart cart = new Cart(); // nanti bisa disesuaikan dengan user yang login
//         CartItem item = new CartItem(produk, 1);
//         cart.addItem(item);

//         cartRepo.save(cart);
//         return "redirect:/home/produk";
//     }
// }
