package com.example.demo.rest;

import com.example.demo.domain.Client;
import com.example.demo.service.ClientService;
import com.example.demo.service.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class ClientRessource {
    @Autowired
    ClientService clientService;


    @GetMapping()
    public ResponseDTO findAllClients(){
        return clientService.findAll();
    }

    @GetMapping("/user")
    public ResponseDTO findClientByEmail(@RequestParam(value = "email", defaultValue = "") String email){
        return clientService.findByEmail(email);
    }

    @PostMapping("/addClient")
    public ResponseDTO save(@RequestBody Client client){
        return clientService.save(client);
    }

    @DeleteMapping("/client")
    public ResponseDTO deleteClient(@RequestParam(value = "id", defaultValue = "") Long id){
        return clientService.deleteById(id);
    }
}
