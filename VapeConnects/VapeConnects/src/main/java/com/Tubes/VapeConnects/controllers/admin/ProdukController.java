package com.Tubes.VapeConnects.controllers.admin;

import com.Tubes.VapeConnects.model.Produk;
import com.Tubes.VapeConnects.service.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/produk")
public class ProdukController {

    private final ProdukService produkService;

    @Autowired
    public ProdukController(ProdukService produkService) {
        this.produkService = produkService;
    }

    // Menampilkan form tambah produk
    @GetMapping("/tambah")
    public String tambahProdukForm(Model model) {
        model.addAttribute("produk", new Produk());
        return "admin/produk/tambah";
    }

    // Menyimpan produk baru
    @PostMapping("/simpan")
    public String simpanProduk(@ModelAttribute Produk produk) {
        produkService.saveProduk(produk);
        return "redirect:/admin/index";
    }

    // Menampilkan form edit produk
    @GetMapping("/edit/{id}")
    public String editProdukForm(@PathVariable Integer id, Model model) {
        Produk produk = produkService.getProdukById(id);
        model.addAttribute("produk", produk);
        return "admin/produk/edit";
    }

    // Menyimpan perubahan produk
    @PostMapping("/update")
    public String updateProduk(@ModelAttribute Produk produk) {
        produkService.saveProduk(produk);
        return "redirect:/admin/index";
    }

    // Menghapus produk
    @GetMapping("/hapus/{id}")
    public String hapusProduk(@PathVariable Integer id) {
        produkService.deleteProduk(id);
        return "redirect:/admin/index";
    }
}