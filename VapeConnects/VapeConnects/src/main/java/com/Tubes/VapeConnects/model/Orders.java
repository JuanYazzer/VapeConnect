package com.Tubes.VapeConnects.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "status")
    private String status;

    @Column(name = "dptitle")
    private String dpTitle; // Deskripsi atau judul pesanan

    @Column(name = "total_amount")
    private Double totalAmount;

    // Constructors
    public Orders() {
    }

    public Orders(Long userId, Date orderDate, String status, String dpTitle, Double totalAmount) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
        this.dpTitle = dpTitle;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDpTitle() {
        return dpTitle;
    }

    public void setDpTitle(String dpTitle) {
        this.dpTitle = dpTitle;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // toString method (optional)
    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", dpTitle='" + dpTitle + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

}