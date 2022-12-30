package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ResponseDTO responseDTO;

    public ResponseDTO save(Product product){
        if (product == null){
            responseDTO.setStatus("404");
            responseDTO.setMessage("All product information are mandatory");
            responseDTO.setData(product);
            return responseDTO;
        }
        productRepository.save(product);
        responseDTO.setStatus("200");
        responseDTO.setMessage("Product has been added successfully");
        responseDTO.setData(product);
        return responseDTO;
    }

    public ResponseDTO findAll(){
        List<Product> productList = productRepository.findAll();
        responseDTO.setStatus("200");
        responseDTO.setMessage("Successful Request");
        responseDTO.setData(productList);
        return responseDTO;
    }

    public ResponseDTO findByName(String name){
        if (name == null){
            responseDTO.setStatus("404");
            responseDTO.setMessage("Bad Request");
            responseDTO.setData("You must set a name");
            return responseDTO;
        }
        Product product = productRepository.findByName(name);
        responseDTO.setStatus("200");
        responseDTO.setMessage("Successful Request");
        responseDTO.setData(product);
        return responseDTO;
    }

    public ResponseDTO deleteById(Long id){
        if (id == null){
            responseDTO.setStatus("404");
            responseDTO.setMessage("Bad Request");
            responseDTO.setData("You must set the product Id");
            return responseDTO;
        }
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
            responseDTO.setStatus("200");
            responseDTO.setMessage("Successful Request");
            responseDTO.setData(product);
        }
        else {
            responseDTO.setStatus("500");
            responseDTO.setMessage("This Product doesn't exist");
        }
        return responseDTO;
    }

}
