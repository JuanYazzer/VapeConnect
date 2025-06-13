package com.Tubes.VapeConnects.controllers;

import com.Tubes.VapeConnects.model.Order;
import com.Tubes.VapeConnects.model.Customer;
import com.Tubes.VapeConnects.model.User;
import com.Tubes.VapeConnects.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produk")
public class ProductController {

    @Autowired
    private OrderRepository orderRepository;

    // Menampilkan produk yang pernah dibeli user (riwayat transaksi)
    @GetMapping("/riwayat")
    public String showOrderHistory(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");

        if (user == null || !(user instanceof Customer)) {
            redirectAttributes.addFlashAttribute("error", "Silakan login terlebih dahulu untuk melihat riwayat.");
            return "redirect:/login";
        }

        Customer customer = (Customer) user;

        // Ambil semua order milik customer
        List<Order> orders = orderRepository.findByCustomerId(Long.valueOf(customer.getId()));
        model.addAttribute("orders", orders);

        return "RiwayatPemesanan/Riwayat"; // Ganti sesuai nama file HTML kamu
    }



    // Menyimpan review
    @PostMapping("/review")
    public String beriRatingOrder(@RequestParam Long orderId,
                                @RequestParam Integer rating,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");

        if (user == null || !(user instanceof Customer)) {
            redirectAttributes.addFlashAttribute("error", "Silakan login terlebih dahulu untuk memberi rating.");
            return "redirect:/login";
        }

        // Ambil order dari database
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Order tidak ditemukan.");
            return "redirect:/produk/riwayat";
        }

        Order order = optionalOrder.get();

        // Cek apakah order milik customer yang login
        if (order.getCustomer().getId() != user.getId()) {
            redirectAttributes.addFlashAttribute("error", "Kamu tidak bisa memberi rating untuk order milik orang lain.");
            return "redirect:/produk/riwayat";
        }

        // Update rating
        order.setRating(rating);
        orderRepository.save(order);

        redirectAttributes.addFlashAttribute("success", "Rating berhasil disimpan.");
        return "redirect:/produk/riwayat";
    }

}
