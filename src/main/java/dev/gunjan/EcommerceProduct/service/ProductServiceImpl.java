package dev.gunjan.EcommerceProduct.service;

import dev.gunjan.EcommerceProduct.dto.CreateProductRequestDTO;
import dev.gunjan.EcommerceProduct.dto.ProductResponseDTO;
import dev.gunjan.EcommerceProduct.entity.Category;
import dev.gunjan.EcommerceProduct.entity.Product;
import dev.gunjan.EcommerceProduct.exception.CategoryNotFoundException;
import dev.gunjan.EcommerceProduct.exception.ProductNotFoundException;
import dev.gunjan.EcommerceProduct.mapper.ProductEntityDTOMapper;
import dev.gunjan.EcommerceProduct.repository.CategoryRepository;
import dev.gunjan.EcommerceProduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> savedProducts = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for(Product product : savedProducts){
            productResponseDTOS.add(ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product));
        }
        return productResponseDTOS;
    }

    @Override
    public ProductResponseDTO getProduct(UUID productId) throws ProductNotFoundException {
//        Product savedProduct =  productRepository.findById(productId).get();
//        if(savedProduct == null){
//            throw new ProductNotFoundException("Product not found for given id");
//        }
//        return savedProduct;

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id :" + productId)
        );
        return  ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product);
    }

    @Override
    public ProductResponseDTO createProduct(CreateProductRequestDTO productRequestDTO) {
        Product product = ProductEntityDTOMapper.convertCreateProductRequestDTOToProduct(productRequestDTO);
        Category savedCategory =  categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow(
                () -> new CategoryNotFoundException("Category not found for id :" + productRequestDTO.getCategoryId())
        );
        product.setCategory(savedCategory);
        product = productRepository.save(product);
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product);
    }

    @Override
    public ProductResponseDTO updateProduct(CreateProductRequestDTO createProductRequestDTO, UUID productId) {

        Product savedProduct = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id :" + productId)
        );
        savedProduct.setTitle(createProductRequestDTO.getTitle());
        savedProduct.setPrice(createProductRequestDTO.getPrice());
        savedProduct.setDescription(createProductRequestDTO.getDescription());
        savedProduct.setImageURL(createProductRequestDTO.getImageURL());
        savedProduct = productRepository.save(savedProduct);
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(savedProduct);
    }

    @Override
    public boolean deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
        return true;

    }

    @Override
    public ProductResponseDTO getProduct(String productName) {
        return  ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(productRepository.findProductByTitle(productName));
    }

    @Override
    public List<Product> getProducts(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice,maxPrice);
    }
}
