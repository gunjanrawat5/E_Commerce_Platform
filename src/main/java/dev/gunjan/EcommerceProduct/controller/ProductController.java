package dev.gunjan.EcommerceProduct.controller;

import dev.gunjan.EcommerceProduct.dto.CreateProductRequestDTO;
import dev.gunjan.EcommerceProduct.dto.ProductResponseDTO;
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
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<ProductResponseDTO> products = productService.getAllProducts();
        return  ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id")UUID id){
        if(id == null){
            throw new ProductNotFoundException("Product with given id does not exist");
        }
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping("/")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody CreateProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(productService.createProduct(productRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("id") UUID id, @RequestBody CreateProductRequestDTO productRequestDTO){
        return ResponseEntity.ok( productService.updateProduct(productRequestDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") UUID id){
         return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/name/{productName}")
    public ResponseEntity<ProductResponseDTO> getProductByProductName(@PathVariable("productName") String productName){
        return ResponseEntity.ok(productService.getProduct(productName));
    }

    @GetMapping("/{min}/{max}")
    public ResponseEntity<List<Product>> getProductByPriceRange(@PathVariable("min") double minPrice,@PathVariable("max") double maxPrice){
        return ResponseEntity.ok(productService.getProducts(minPrice,maxPrice));
    }



}
