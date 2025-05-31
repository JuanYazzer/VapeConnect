package com.Tubes.VapeConnects.controllers;

import com.Tubes.VapeConnects.model.Produk;
import com.Tubes.VapeConnects.model.Review;
import com.Tubes.VapeConnects.service.OrderItemService;
import com.Tubes.VapeConnects.service.ProdukService;
import com.Tubes.VapeConnects.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/produk")
public class ProductController {

    @Autowired
    private ProdukService productService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ReviewService reviewService;

    // Menampilkan hanya produk yang sudah pernah dibeli
    @GetMapping
    public String showProducts(Model model) {
        List<Produk> allProducts = productService.getAllProduk();

        // Filter hanya produk dengan jumlah terjual > 0
        List<Produk> soldProducts = allProducts.stream()
    .filter(p -> orderItemService.getTotalQuantityByProductId(p.getId().longValue()) > 0)
    .collect(Collectors.toList());


        model.addAttribute("products", soldProducts);
        return "RiwayatPemesanan/Riwayat";
    }

    // Menyimpan rating/review user terhadap produk
    @PostMapping("/review")
    public String submitReview(@RequestParam Long productId,
                               @RequestParam Integer rating,
                               Principal principal) {
        Review review = new Review();
        review.setRating(rating);
        // Jika kamu ingin menghubungkan dengan user atau produk, tambahkan di sini
        reviewService.saveReview(review);

        return "redirect:/produk"; // Redirect kembali ke halaman produk
    }
}
