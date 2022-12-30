package com.example.demo.domain;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull @NotEmpty
    private String refProduct;
    @NotNull
    private Integer quantity;
    @NotNull(message = "price is mandatory")
    private Double price;

    @ManyToOne
    private Command command;

    public OrderItem() {
    }

    public OrderItem(String refProduct, Integer quantity, Double price, Command command) {
        this.refProduct = refProduct;
        this.quantity = quantity;
        this.price = price;
        this.command = command;
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

    public Command getOrder() {
        return command;
    }

    public void setOrder(Command command) {
        this.command = command;
    }
}
