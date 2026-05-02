package dev.gunjan.EcommerceProduct.controller;

import dev.gunjan.EcommerceProduct.entity.Product;
import dev.gunjan.EcommerceProduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<Product> getAllProducts(){
        return  null;
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product savedProduct = productService.createProduct(product);
        return  ResponseEntity.ok(savedProduct);
    }

}
