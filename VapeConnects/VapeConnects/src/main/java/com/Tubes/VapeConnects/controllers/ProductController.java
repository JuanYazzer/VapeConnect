package com.Tubes.VapeConnects.controllers;

import com.Tubes.VapeConnects.model.Produk;
import com.Tubes.VapeConnects.model.Review;
import com.Tubes.VapeConnects.model.User;
import com.Tubes.VapeConnects.service.OrderItemService;
import com.Tubes.VapeConnects.service.ProdukService;
import com.Tubes.VapeConnects.service.ReviewService;
import com.Tubes.VapeConnects.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showProducts(Model model) {
        List<Produk> allProducts = productService.getAllProduk();
        List<Produk> soldProducts = allProducts.stream()
                .filter(p -> orderItemService.getTotalQuantityByProductId(p.getId().longValue()) > 0)
                .collect(Collectors.toList());

        model.addAttribute("products", soldProducts);
        return "RiwayatPemesanan/Riwayat";
    }

    @PostMapping("/review")
    public String submitReview(@RequestParam int productId,
                               @RequestParam Integer rating,
                               Principal principal,
                               RedirectAttributes redirectAttributes) {

        String username = principal.getName();

        if (reviewService.hasUserReviewedProduct(username, productId)) {
            redirectAttributes.addFlashAttribute("error", "Kamu sudah memberi review untuk produk ini.");
            return "redirect:/produk";
        }

        User user = userRepository.findByUsername(username);
        Produk product = productService.getProdukById(productId);

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setRating(rating);

        reviewService.saveReview(review);

        redirectAttributes.addFlashAttribute("success", "Review berhasil disimpan.");
        return "redirect:/produk";
    }
}
