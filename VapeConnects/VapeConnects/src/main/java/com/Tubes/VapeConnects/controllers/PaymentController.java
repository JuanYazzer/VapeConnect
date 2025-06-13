// PaymentController.java
package com.Tubes.VapeConnects.controllers;

import com.Tubes.VapeConnects.model.Cart;
import com.Tubes.VapeConnects.model.Customer;
import com.Tubes.VapeConnects.model.Payment;
import com.Tubes.VapeConnects.model.Order;
import com.Tubes.VapeConnects.repository.PaymentRepository;
import com.Tubes.VapeConnects.repository.CartRepository;
import com.Tubes.VapeConnects.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDateTime;

@Controller
@SessionAttributes("cart")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/payment")
    public String showPaymentForm(Model model, HttpSession session) {
        // Misal ada total dari session atau model
        model.addAttribute("total", 100000.0); // Ganti sesuai logika real kamu
        return "pembayaran/pay-form"; // file HTML thymeleaf
    }

   @PostMapping("/payment/submit")
    public String submitPayment(@RequestParam("paymentMethod") String paymentMethod,
                                HttpSession session, Model model) {

        Customer user = (Customer) session.getAttribute("user");

        Cart cart = cartRepository.findById(user.getCart().getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        double total = cart.calculateTotal();

        // Buat dan simpan Order
        Order order = new Order();
        order.setCustomer(user);
        order.setTotal(total);
        order.setPaymentMethod(paymentMethod);
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);

        // Buat dan simpan Payment
        Payment payment = new Payment();
        payment.setPaymentMethod(paymentMethod);
        payment.setTotal(total);
        payment.setTanggal(java.util.Date.from(LocalDateTime.now().atZone(java.time.ZoneId.systemDefault()).toInstant()));
        payment.setCustomer(user);
        payment.setOrder(order);
        paymentRepository.save(payment);

        // Kosongkan keranjang
        cart.getItems().clear();
        cartRepository.save(cart);

        return "redirect:/pembayaran/succes";
    }


}



