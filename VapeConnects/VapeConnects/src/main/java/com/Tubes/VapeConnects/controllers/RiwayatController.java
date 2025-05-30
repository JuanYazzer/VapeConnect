@Controller
public class TransaksiController {

    @GetMapping("/riwayat")
    public String tampilRiwayat(Model model) {
        List<Transaksi> riwayat = transaksiService.getRiwayatTransaksi();
        model.addAttribute("riwayat", riwayat);
        return "Riwayat_Transaksi"; // Akan diarahkan ke Riwayat_Transaksi.jsp
    }
}
