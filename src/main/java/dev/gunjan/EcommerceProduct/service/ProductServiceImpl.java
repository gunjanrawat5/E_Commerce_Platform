package dev.gunjan.EcommerceProduct.service;

import dev.gunjan.EcommerceProduct.entity.Product;
import dev.gunjan.EcommerceProduct.exception.ProductNotFoundException;
import dev.gunjan.EcommerceProduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(UUID productId) throws ProductNotFoundException {
//        Product savedProduct =  productRepository.findById(productId).get();
//        if(savedProduct == null){
//            throw new ProductNotFoundException("Product not found for given id");
//        }
//        return savedProduct;

        return productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id :" + productId)
        );
    }

    @Override
    public Product createProduct(Product product) {
        return  productRepository.save(product);

    }

    @Override
    public Product updateProduct(Product updatedProduct, UUID productId) {

        Product savedProduct = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id :" + productId)
        );
        savedProduct.setTitle(updatedProduct.getTitle());
        savedProduct.setCategory(updatedProduct.getCategory());
        savedProduct.setRating(updatedProduct.getRating());
        savedProduct.setPrice(updatedProduct.getPrice());
        savedProduct.setDescription(updatedProduct.getDescription());
        savedProduct.setImageURL(updatedProduct.getImageURL());
        return  savedProduct;
    }

    @Override
    public boolean deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
        return true;

    }

    @Override
    public Product getProduct(String productName) {
        return productRepository.findProductByTitle(productName);
    }

    @Override
    public List<Product> getProducts(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice,maxPrice);
    }
}
