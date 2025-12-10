package com.Tubes.VapeConnects.controllers;

import java.util.List;

import jakarta.servlet.http.HttpSession; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.Tubes.VapeConnects.model.Produk;
import com.Tubes.VapeConnects.repository.ProdukRepository;

@Controller
public class StatusController {
    @GetMapping("/Status")
    public String viewStatus(){
        return "StatusPemesanan/Status";
    } 
}
