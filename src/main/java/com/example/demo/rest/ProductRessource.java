package com.example.demo.rest;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRessource {
    @Autowired
    ProductService productService;


    @GetMapping()
    public ResponseDTO findAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/product")
    public ResponseDTO findProductByName(@RequestParam(value = "name", defaultValue = "") String name){
        return productService.findByName(name);
    }

    @PostMapping("/addProduct")
    public ResponseDTO save(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/product")
    public ResponseDTO deleteProduct(@RequestParam(value = "id", defaultValue = "") Long id){
        return productService.deleteById(id);
    }
}
