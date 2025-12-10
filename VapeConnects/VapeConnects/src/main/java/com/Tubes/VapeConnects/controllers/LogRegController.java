package com.Tubes.VapeConnects.controllers;

import com.Tubes.VapeConnects.model.User;
import com.Tubes.VapeConnects.model.Admin;

import java.time.LocalDate;
import java.time.Period;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Tubes.VapeConnects.repository.UserRepository;
import com.Tubes.VapeConnects.repository.AdminRepository;

@Controller
public class LogRegController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    // ==================== LOGIN ====================

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
        Admin admin = adminRepository.findByUsername(username);

        // LOGIN ADMIN
        if (admin != null && admin.getPassword().equals(password)) {
            session.setAttribute("username", admin.getUsername());
            session.setAttribute("role", "admin");
            return "redirect:/admin/index";
        }

        // LOGIN USER
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("role", "user");
            return "redirect:/home/home";
        }

        // GAGAL LOGIN
        model.addAttribute("error", "Username atau password salah.");
        return "Login";
    }

    // ==================== REGISTER ====================

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

        // cek username sudah dipakai atau belum
        if (userRepository.findByUsername(username) != null) {
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

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setAge(age);

        userRepository.save(newUser);

        return "redirect:/login";
    }

    // ==================== LOGOUT ====================

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // hapus semua session (user, role, username, dll)
        return "redirect:/login";
    }
}
