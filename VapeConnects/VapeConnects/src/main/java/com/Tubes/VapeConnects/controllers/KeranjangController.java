package com.Tubes.VapeConnects.controllers;

import com.Tubes.VapeConnects.model.*;
import com.Tubes.VapeConnects.repository.CartRepository;
import com.Tubes.VapeConnects.repository.ProdukRepository;
import com.Tubes.VapeConnects.service.CartService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/keranjang")
public class KeranjangController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;
    @Autowired
    private ProdukRepository produkRepo;

  @GetMapping("/keranjang")
    public String viewCart(Model model, HttpSession session) {
        Customer user = (Customer) session.getAttribute("user");

        Cart cart = cartRepository.findById(user.getCart().getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        BigDecimal totalHarga = cart.getItems().stream()
                .filter(item -> item.getProduk() != null)
                .map(item -> item.getProduk().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("keranjangList", cart.getItems());
        model.addAttribute("totalHarga", totalHarga);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "home/keranjang";
    }

    
    @PostMapping("/tambah")
    public String addToCart(@SessionAttribute("user") User user, @RequestParam int productId) {
        Produk produk = produkRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Produk tidak ditemukan"));

        if (produk.getStock() <= 0) {
            return "redirect:/home/produk?error=stokHabis";
        }

        Customer customer = (Customer) user;
        Cart cart = customer.getCart();

        // Ambil dari DB langsung (bukan dari cart.getItems() yang bisa stale)
        Optional<CartItem> optionalItem = cartService.findCartItem(cart.getId(), productId);

        if (optionalItem.isPresent()) {
            CartItem item = optionalItem.get();
            int newQuantity = item.getQuantity() + 1;

            if (newQuantity > produk.getStock()) {
                return "redirect:/home/produk?error=stokLimit";
            }

            item.setQuantity(newQuantity);
            cartService.saveItem(item); // update
        } else {
            if (produk.getStock() < 1) {
                return "redirect:/home/produk?error=stokHabis";
            }

            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduk(produk);
            newItem.setQuantity(1);
            cartService.saveItem(newItem); // insert baru
        }

        return "redirect:/home/produk?success=tambah";
    }

    @PostMapping("/tambah/{itemId}")
    public String tambahItem(@PathVariable int itemId) {
        cartService.tambahJumlah(itemId);
        return "redirect:/keranjang/keranjang";
    }

    @PostMapping("/kurang/{itemId}")
    public String kurangItem(@PathVariable int itemId) {
        cartService.kurangJumlah(itemId);
        return "redirect:/keranjang/keranjang";
    }
}
