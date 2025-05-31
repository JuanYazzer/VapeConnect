package com.Tubes.VapeConnects.controllers;

import com.Tubes.VapeConnects.model.User;
import com.Tubes.VapeConnects.model.Admin;

import java.time.LocalDate;
import java.time.Period;

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
                        Model model) {

        User user = userRepository.findByUsername(username);
        Admin admin = adminRepository.findByUsername(username); 

        if (admin != null && admin.getPassword().equals(password)) {
            return "redirect:/admin/index"; // ngarah ke halaman user 
        }

        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/home/produk"; // ngarah ke halaman user 
        } else {
            model.addAttribute("error", "Username atau password salah.");
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

    // nyimpan user baru
    User newUser = new User();
    newUser.setUsername(username);
    newUser.setEmail(email);
    newUser.setPassword(password);
    newUser.setAge(age); // masih disimpan sebagai string

    userRepository.save(newUser);

    return "redirect:/login";
}

   

}

//tambahin untuk kalau umur <= 18 tahun gabisa buat akun dan buat lagi untuk enkripsi password
//tambahin lagi alert untuk usernme dan email sudah terpakai
