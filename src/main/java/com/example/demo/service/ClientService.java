package com.example.demo.service;

import com.example.demo.domain.Client;
import com.example.demo.domain.Product;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service @Slf4j @RequiredArgsConstructor
public class ClientService implements UserDetailsService {
    @Autowired
    ClientRepository clientRepository;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    ResponseDTO responseDTO;

    public ResponseDTO save(Client client){
        if (client == null){
            responseDTO.setStatus("404");
            responseDTO.setMessage("All Client informations are mandatory");
            responseDTO.setData(client);
            return responseDTO;
        }
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
        responseDTO.setStatus("200");
        responseDTO.setMessage("Client has been added successfully");
        responseDTO.setData(client);
        return responseDTO;
    }

    public ResponseDTO findAll(){
        List<Client> clientList = clientRepository.findAll();
        responseDTO.setStatus("200");
        responseDTO.setMessage("Successful Request");
        responseDTO.setData(clientList);
        return responseDTO;
    }

    public ResponseDTO findByEmail(String email){
        if (email == null){
            responseDTO.setStatus("404");
            responseDTO.setMessage("Bad Request");
            responseDTO.setData("You must set a name");
            return responseDTO;
        }
        Client client = clientRepository.findByEmail(email);
        responseDTO.setStatus("200");
        responseDTO.setMessage("Successful Request");
        responseDTO.setData(client);
        return responseDTO;
    }

    public ResponseDTO deleteById(Long id){
        if (id == null){
            responseDTO.setStatus("404");
            responseDTO.setMessage("Bad Request");
            responseDTO.setData("You must set the client Id");
            return responseDTO;
        }
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()){
            clientRepository.deleteById(id);
            responseDTO.setStatus("200");
            responseDTO.setMessage("Successful Request");
            responseDTO.setData(client);
        }
        else {
            responseDTO.setStatus("500");
            responseDTO.setMessage("This Client doesn't exist");
        }
        return responseDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(username);
        if (client == null){
            throw new UsernameNotFoundException("User not found");
        }
        else {
            log.error("User found : >>> {}", username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(client.getRole().getName()));
            return new org.springframework.security.core.userdetails.User(client.getEmail(), client.getPassword(), authorities);
        }
    }
}
