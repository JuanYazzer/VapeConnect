package com.Tubes.VapeConnects.controllers;

import com.Tubes.VapeConnects.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.Tubes.VapeConnects.repository.UserRepository;

@Controller
public class LogRegController {

    @Autowired
    private UserRepository userRepository;

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
        if (user != null && user.getPassword().equals(password)) {
            if (username.toLowerCase().contains("admin")) {
                return "redirect:/admin/index"; // Ganti dengan halaman admin-mu
            } else {
                return "redirect:/home/home"; // Ganti dengan halaman user biasa
            }
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

        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username sudah digunakan.");
            return "Register";
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password); // untuk keamanan sebaiknya hash
        newUser.setAge(age);

        userRepository.save(newUser);

        return "redirect:/login";
    }
   

}

//tambahin untuk kalau umur <= 18 tahun gabisa buat akun dan buat lagi untuk enkripsi password
//tambahin lagi alert untuk usernme dan email sudah terpakai
