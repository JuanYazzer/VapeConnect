package com.Tubes.VapeConnects.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogRegController {

    @GetMapping("/")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "Login";
	}
    @PostMapping("/login")
    public String loginPost(@RequestParam(name="username") String username, 
                            @RequestParam(name="password") String password, Model model) {
        if (username.equals("admin") && password.equals("admin")) {
            return "redirect:/home/produk";  // Redirect ke /home/produk
        } else {
            model.addAttribute("error", "Username atau password salah.");
            return "Login";
        }
    }


    @GetMapping("/register")
    public String register() {
        return "Register";
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    }
}
