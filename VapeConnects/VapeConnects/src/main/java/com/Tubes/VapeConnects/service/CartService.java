package com.Tubes.VapeConnects.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.Tubes.VapeConnects.model.Cart;
import com.Tubes.VapeConnects.model.CartItem;
import com.Tubes.VapeConnects.repository.CartItemRepository;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public void tambahJumlah(Integer cartItemId) {
        cartItemRepository.findById(cartItemId).ifPresent(item -> {
            item.setQuantity(item.getQuantity() + 1);
            cartItemRepository.save(item); // Tidak perlu setSubTotal
        });
    }

    public void kurangJumlah(int cartItemId) {
        CartItem item = cartItemRepository.findById(cartItemId).orElseThrow();
        Cart cart = item.getCart();

        int newQty = item.getQuantity() - 1;

        if (newQty <= 0) {
            cart.getItems().remove(item); // ini trigger orphanRemoval
            // Tidak perlu delete manual!
        } else {
            item.setQuantity(newQty);
        }

        // Simpan cart, biar Hibernate handle semua perubahan child (CartItem)
        // asalkan cascade dan orphanRemoval sudah benar (dan sudah ✅ di kamu)
        cartItemRepository.save(item); // optional: bisa hapus ini juga kalau cart sudah di-save
    }
    public Optional<CartItem> findCartItem(Integer cartId, Integer produkId) {
        return cartItemRepository.findByCartIdAndProdukId(cartId, produkId);
    }

    public void saveItem(CartItem item) {
        cartItemRepository.save(item);
    }

}
