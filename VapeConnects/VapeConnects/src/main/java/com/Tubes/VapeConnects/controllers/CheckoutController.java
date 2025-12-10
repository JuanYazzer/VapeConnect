package com.Tubes.VapeConnects.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

import com.Tubes.VapeConnects.model.Cart;
import com.Tubes.VapeConnects.model.Customer;
import com.Tubes.VapeConnects.repository.CartRepository;

@Controller
public class CheckoutController {
    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/checkout")
    public String viewCheckout(HttpSession session, Model model) {
        Customer user = (Customer) session.getAttribute("user");

        if (user == null || user.getCart() == null) {
            return "redirect:/keranjang/keranjang";
        }

        // Fetch ulang cart dari database untuk memastikan data valid dan up-to-date
        Cart cart = cartRepository.findById(user.getCart().getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            return "redirect:/keranjang/keranjang";
        }

        model.addAttribute("customer", user);
        model.addAttribute("cart", cart);
        return "pembayaran/method";
    }


    @GetMapping("/pembayaran/succes")
    public String viewSuccess() {
        return "pembayaran/succes"; // Mengembalikan nama view untuk halaman sukses pembayaran
    }
}
