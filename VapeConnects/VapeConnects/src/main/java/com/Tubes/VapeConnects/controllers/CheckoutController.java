package com.Tubes.VapeConnects.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {

    @GetMapping("/checkout")
    public String viewCheckout() {
        return "pembayaran/method"; // Mengembalikan nama view untuk halaman checkout
    }
}
