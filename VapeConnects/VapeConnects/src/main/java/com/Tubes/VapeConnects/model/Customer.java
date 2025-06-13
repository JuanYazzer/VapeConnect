package com.Tubes.VapeConnects.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id") // ini yang penting!
    private Cart cart;


    private String age; 
    
    public Customer(int id, String username, String email, String password) {
        super(id, username, email, password);
    }

    // Overloaded constructor untuk Customer dengan Cart yang sudah ada
    public Customer(int id, String username, String email, String password, Cart cart) {
        super(id, username, email, password);
        this.cart = cart != null ? cart : new Cart();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", cart=" + cart +
                '}';
    }
}
