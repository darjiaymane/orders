package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String phone;
    private String email;
    private String password;
    @OneToMany(mappedBy = "client")
    private List<Command> commandList;
    @ManyToOne
    private Role role;


    public Client(Long id, String username, String phone, String email, String password, List<Command> commandList) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.commandList = commandList;
    }
    public Client(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @JsonIgnore
    public List<Command> getOrderList() {
        return commandList;
    }
    @JsonSetter
    public void setOrderList(List<Command> commandList) {
        this.commandList = commandList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
