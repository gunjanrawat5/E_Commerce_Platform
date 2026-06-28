package dev.gunjan.EcommerceProduct.service;

import dev.gunjan.EcommerceProduct.entity.Product;
import dev.gunjan.EcommerceProduct.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProduct(UUID productId) throws ProductNotFoundException;
    Product createProduct(Product product);
    Product updateProduct(Product updatedProduct, UUID productId);
    boolean deleteProduct(UUID productId);
    Product getProduct(String productName);
    List<Product> getProducts(double minPrice, double maxPrice);
}
