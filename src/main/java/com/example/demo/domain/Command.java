package com.example.demo.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Command implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private UUID uuid;

    private LocalDateTime createdAt;

    private Double totalPrice;

    @OneToMany(mappedBy = "command")
    private Set<OrderItem> orderItems = new HashSet<>();
    @ManyToOne
    private Client client;

    public Command(UUID uuid, LocalDateTime createdAt, Double totalPrice, @NotNull @NotEmpty Set<OrderItem> orderItems) {
        this.uuid = uuid;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }

    public Command() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }
    @JsonSetter
    public void setOrderItems (Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
