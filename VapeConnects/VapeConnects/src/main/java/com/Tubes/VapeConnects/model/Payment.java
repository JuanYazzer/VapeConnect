package com.Tubes.VapeConnects.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Payment {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPayment;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    private double total;

    private String paymentMethod;

    @Temporal(TemporalType.DATE)
    private Date tanggal;

    private String note;

    // Getter & Setter

    public Integer getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

