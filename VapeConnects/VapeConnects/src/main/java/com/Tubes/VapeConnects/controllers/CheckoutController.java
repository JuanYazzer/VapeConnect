package com.Tubes.VapeConnects.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

import com.Tubes.VapeConnects.model.Cart;
import com.Tubes.VapeConnects.model.Order;
import com.Tubes.VapeConnects.model.CartItem;
import com.Tubes.VapeConnects.model.Customer;
import com.Tubes.VapeConnects.model.OrderItem;
import com.Tubes.VapeConnects.repository.CartRepository;
import com.midtrans.httpclient.error.MidtransError;

import com.Tubes.VapeConnects.repository.OrderRepository;
import com.Tubes.VapeConnects.service.PaymentServices;


import org.springframework.beans.factory.annotation.Value;
import java.util.List;

@Controller
public class CheckoutController {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentServices paymentService;

    @Value("${midtrans.client.key}")
    private String clientKey;

    @GetMapping("/checkout")
    public String showCheckoutForm(Model model, HttpSession session) {

        Customer user = (Customer) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Cart cart = user.getCart();
        if (cart == null || cart.getItems().isEmpty())
            return "redirect:/keranjang";

        model.addAttribute("total", cart.calculateTotal());

        return "checkout/checkout-form"; // buat file ini nanti
    }


    @PostMapping("/checkout/submit")
    public String submitCheckout(
            @RequestParam String shippingName,
            @RequestParam String shippingEmail,
            @RequestParam String shippingPhone,
            @RequestParam String shippingAddress,
            @RequestParam String paymentMethod,
            HttpSession session,
            Model model
    ) {
        Customer user = (Customer) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Cart cart = user.getCart();
        if (cart == null || cart.getItems().isEmpty())
            return "redirect:/keranjang";

        double total = cart.calculateTotal();

        // ===============================
        // 1. Create Order
        // ===============================
        Order order = new Order();
        order.setCustomer(user);
        order.setTotal(total);
        order.setPaymentMethod(paymentMethod);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        // set shipping snapshot
        order.setShippingName(shippingName);
        order.setShippingEmail(shippingEmail);
        order.setShippingPhone(shippingPhone);
        order.setShippingAddress(shippingAddress);

        orderRepository.save(order);

        // ===============================
        // 2. Buat OrderItem
        // ===============================
        List<OrderItem> items = new ArrayList<>();

        for (CartItem c : cart.getItems()) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(c.getProduk());
            item.setQuantity(c.getQuantity());
            item.setPrice(c.getProduk().getPrice().doubleValue());
            items.add(item);
        }

        order.setItems(items);
        orderRepository.save(order);

        // ===============================
        // 3. Generate SNAP TOKEN
        // ===============================
        try {
            String snapToken = paymentService.createSnapToken(order);
            order.setSnapToken(snapToken);
            orderRepository.save(order);

            model.addAttribute("clientKey", clientKey);
            model.addAttribute("snapToken", snapToken);

        } catch (MidtransError e) {
            throw new RuntimeException("Gagal membuat snap token: " + e.getMessage());
        }

        // ===============================
        // 4. Kosongkan keranjang
        // ===============================
        cart.getItems().clear();
        cartRepository.save(cart);

        return "pembayaran/pay-snap";
    }

    @GetMapping("/pembayaran/succes")
    public String viewSuccess() {
        return "pembayaran/succes"; // Mengembalikan nama view untuk halaman sukses pembayaran
    }
}
