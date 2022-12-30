package com.example.demo.domain;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull @NotEmpty
    private String refProduct;
    @NotNull @NotEmpty
    private String name;
    @NotNull
    private Integer quantity;
    @NotNull(message = "price is mandatory")
    private Double price;

    @ManyToOne
    private OrderItem orderItem;

    public Product() {
    }

    public Product(String refProduct, Integer quantity, Double price, OrderItem orderItem) {
        this.refProduct = refProduct;
        this.quantity = quantity;
        this.price = price;
        this.orderItem = orderItem;
    }

    public String getRefProduct() {
        return refProduct;
    }

    public void setRefProduct(String refProduct) {
        this.refProduct = refProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", refProduct='" + refProduct + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orderItem=" + orderItem +
                '}';
    }
}
