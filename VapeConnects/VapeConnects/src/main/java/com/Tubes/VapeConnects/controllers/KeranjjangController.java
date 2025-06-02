package com.Tubes.VapeConnects.controllers;

import com.Tubes.VapeConnects.model.*;
import com.Tubes.VapeConnects.repository.CartRepository;
import com.Tubes.VapeConnects.repository.ProdukRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/keranjang")
public class KeranjjangController {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private ProdukRepository produkRepo;

    @PostMapping("/tambah")
    public String addToCart(@SessionAttribute("user") User user, @RequestParam int productId) {
        // logic menambahkan item
        Produk produk = produkRepo.findById(productId).orElseThrow(() -> new IllegalArgumentException("Produk tidak ditemukan"));
        CartItem cartItem = new CartItem();
        cartItem.setProduk(produk);
        Cart cart = cartRepo.findByUser(user).orElse(new Cart());

        cartItem.setProduk(produk);
        cartItem.setQuantity(1); // default quantity
        cart.addItem(cartItem);

        cart.setUser(user);
        cartRepo.save(cart);
        // Contoh: setelah menambah ke keranjang, redirect ke halaman keranjang
        return "redirect:home/produk";
    }

}
