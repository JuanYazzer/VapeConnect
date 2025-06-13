package com.Tubes.VapeConnects.controllers;

import com.Tubes.VapeConnects.model.Customer;
import com.Tubes.VapeConnects.model.Admin;
import com.Tubes.VapeConnects.model.Cart;
import com.Tubes.VapeConnects.model.User;

import java.time.LocalDate;
import java.time.Period;
import jakarta.servlet.http.HttpSession; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.Tubes.VapeConnects.repository.CustomerRepository;
import com.Tubes.VapeConnects.repository.AdminRepository;
import com.Tubes.VapeConnects.repository.CartRepository;
import com.Tubes.VapeConnects.repository.UserRepository;

@Controller
public class LogRegController {

    @Autowired
    private CustomerRepository CustomerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/")
    public String greeting() {
        return "Login";
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    } 

    @PostMapping("/login")
    public String loginPost(@RequestParam String username,
                            @RequestParam String password,
                            Model model,
                            HttpSession session) {

        User user = userRepository.findByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Username atau password salah.");
            return "Login";
        }

        session.setAttribute("user", user);
        session.setAttribute("username", user.getUsername());

        if (user instanceof Admin) {
            session.setAttribute("role", "admin");
            return "redirect:/admin/index";
        } else if (user instanceof Customer) {
            session.setAttribute("role", "user");
            return "redirect:/home/home";
        } else {
            model.addAttribute("error", "Role tidak dikenali.");
            return "Login";
        }
    }


    @GetMapping("/register")
    public String register() {
        return "Register";
    }

    @PostMapping("/register")
    public String registerPost(@RequestParam String username,
                            @RequestParam String email,
                            @RequestParam String password,
                            @RequestParam String age,
                            Model model) {

        // ngecek apakah username udah digunakan
        if (CustomerRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username sudah digunakan.");
            return "Register";
        }

        LocalDate birthDate = LocalDate.parse(age); 
        LocalDate now = LocalDate.now();
        int userAge = Period.between(birthDate, now).getYears();

        if (userAge < 18) {
            model.addAttribute("error", "Usia minimal harus 18 tahun.");
            return "Register";
        }

        // nyimpan user baru
        Customer newUser = new Customer();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setAge(age);
        Cart cart = new Cart();
        newUser.setCart(cart);      // assign cart ke customer
        cart.setCustomer(newUser);  // assign customer ke cart
        CustomerRepository.save(newUser);   // karena cascade di relasi customer→cart, ini udah cukup
        cartRepository.save(cart); 
        return "redirect:/login";
    }
}

