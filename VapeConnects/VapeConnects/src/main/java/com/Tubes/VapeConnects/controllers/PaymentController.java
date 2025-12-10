package com.Tubes.VapeConnects.controllers;

import com.Tubes.VapeConnects.model.Cart;
import com.Tubes.VapeConnects.model.CartItem;
import com.Tubes.VapeConnects.model.Customer;
import com.Tubes.VapeConnects.model.Payment;
import com.Tubes.VapeConnects.model.Produk;
import com.Tubes.VapeConnects.model.Order;
import com.Tubes.VapeConnects.model.OrderItem;
import com.Tubes.VapeConnects.repository.PaymentRepository;
import com.Tubes.VapeConnects.repository.CartRepository;
import com.Tubes.VapeConnects.repository.OrderRepository;
import com.Tubes.VapeConnects.repository.ProdukRepository;
import com.Tubes.VapeConnects.service.PaymentServices;
import com.midtrans.httpclient.error.MidtransError;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("cart")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProdukRepository produkRepository;

    @Autowired
    private PaymentServices paymentService;

    @Value("${midtrans.client.key}")
    private String clientKey;

    // STEP 1: Tampilkan halaman form pembayaran (tombol Pay)
    @GetMapping("/payment")
    public String showPaymentForm(Model model, HttpSession session) {

        Customer user = (Customer) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Cart cart = cartRepository.findById(user.getCart().getId())
                .orElse(null);

        double total = cart != null ? cart.calculateTotal() : 0;

        model.addAttribute("total", total);
        return "pembayaran/pay-form";
    }

    // STEP 2: Submit payment → buat Order + Payment → generate Snap Token
    @PostMapping("/payment/submit")
    public String submitPayment(@RequestParam("paymentMethod") String paymentMethod,
                                HttpSession session, Model model) {

        Customer user = (Customer) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Cart cart = cartRepository.findById(user.getCart().getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        double total = cart.calculateTotal();

        // ============================
        // 1. Buat Order
        // ============================
        Order order = new Order();
        order.setCustomer(user);
        order.setTotal(total);
        order.setPaymentMethod(paymentMethod);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Pending");
        order.setRating(0);
        orderRepository.save(order);

        // ============================
        // 2. Buat OrderItem dari Cart
        // ============================
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduk());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduk().getPrice().doubleValue());

            orderItems.add(orderItem);
        }

        // attach ke order
        order.setItems(orderItems);
        orderRepository.save(order);

        // ============================
        // 2. Kurangi stok produk
        // ============================
        for (CartItem item : cart.getItems()) {
            Produk produk = item.getProduk();

            int qty = item.getQuantity();
            if (produk.getStock() < qty) {
                throw new RuntimeException("Stok tidak cukup untuk " + produk.getName());
            }

            produk.setStock(produk.getStock() - qty);
            produkRepository.save(produk);
        }

        // ============================
        // 3. Buat Payment (pending)
        // ============================
        Payment payment = new Payment();
        payment.setPaymentMethod(paymentMethod);
        payment.setTotal(total);
        payment.setTanggal(java.util.Date.from(LocalDateTime.now()
                .atZone(java.time.ZoneId.systemDefault())
                .toInstant()));
        payment.setCustomer(user);
        payment.setOrder(order);
        paymentRepository.save(payment);

        // ============================
        // 4. Generate SNAP Token
        // ============================
        try {
            String snapToken = paymentService.createSnapToken(order);
            model.addAttribute("snapToken", snapToken);
            model.addAttribute("clientKey", clientKey);
        } catch (MidtransError e) {
            throw new RuntimeException("Gagal membuat snap token: " + e.getMessage());
        }

        // ============================
        // 5. Kosongkan keranjang
        // ============================
        cart.getItems().clear();
        cartRepository.save(cart);

        // ============================
        // 6. Tampilkan halaman Snap
        // ============================

        return "pembayaran/pay-snap"; // HTML untuk pop-up Midtrans
    }

    @PostMapping("/payment/token")
    @ResponseBody
    public String getSnapToken(HttpSession session) throws MidtransError {

        Customer user = (Customer) session.getAttribute("user");
        if (user == null) return null;

        Cart cart = cartRepository.findById(user.getCart().getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        double total = cart.calculateTotal();

        Order order = new Order();
        order.setCustomer(user);
        order.setTotal(total);
        order.setPaymentMethod("Midtrans");
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Pending");
        orderRepository.save(order);

        return paymentService.createSnapToken(order);
    }

}
