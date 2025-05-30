package com.Tubes.VapeConnects.controllers;

import com.Tubes.VapeConnects.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    private final DashboardService dashboardService;

    @Autowired
    public AdminController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }
    
    @GetMapping("/index")
    public String index(Model model) {
        // Ambil data statistik dari service
        Map<String, Object> stats = dashboardService.getDashboardStats();
        
        // Tambahkan data ke model
        model.addAttribute("totalProducts", stats.get("totalProducts"));
        model.addAttribute("lowStockProducts", stats.get("lowStockProducts"));
        model.addAttribute("bestSellers", stats.get("bestSellers"));
        model.addAttribute("totalUsers", stats.get("totalUsers"));
        model.addAttribute("allProducts", stats.get("allProducts"));
        model.addAttribute("totalRevenue", stats.get("totalRevenue"));
        
        return "admin/index";
    }
}