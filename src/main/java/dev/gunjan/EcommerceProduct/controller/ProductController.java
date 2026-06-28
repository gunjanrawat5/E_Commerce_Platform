package dev.gunjan.EcommerceProduct.controller;

import dev.gunjan.EcommerceProduct.entity.Product;
import dev.gunjan.EcommerceProduct.exception.ProductNotFoundException;
import dev.gunjan.EcommerceProduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        return  ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id")UUID id){
        if(id == null){
            throw new ProductNotFoundException("Product with given id does not exist");
        }
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product savedProduct = productService.createProduct(product);
        return  ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") UUID id, @RequestBody Product product){
        Product updatedProduct = productService.updateProduct(product, id);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") UUID id){
         return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/name/{productName}")
    public ResponseEntity<Product> getProductByProductName(@PathVariable("productName") String productName){
        return ResponseEntity.ok(productService.getProduct(productName));
    }

    @GetMapping("/{min}/{max}")
    public ResponseEntity<List<Product>> getProductByPriceRange(@PathVariable("min") double minPrice,@PathVariable("max") double maxPrice){
        return ResponseEntity.ok(productService.getProducts(minPrice,maxPrice));
    }



}
