package dev.gunjan.EcommerceProduct.mapper;

import dev.gunjan.EcommerceProduct.dto.CreateProductRequestDTO;
import dev.gunjan.EcommerceProduct.dto.ProductResponseDTO;
import dev.gunjan.EcommerceProduct.entity.Product;

public class ProductEntityDTOMapper {
    public static ProductResponseDTO convertProductEntityToProductResponseDTO(Product product){
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setTitle(product.getTitle());
        responseDTO.setCategory(product.getCategory().getName());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setRating(product.getRating());
        responseDTO.setImageURL(product.getImageURL());
        return responseDTO;

    }

    public static Product convertCreateProductRequestDTOToProduct(CreateProductRequestDTO productRequestDTO){
        Product product = new Product();
        product.setTitle(productRequestDTO.getTitle());
        product.setPrice(productRequestDTO.getPrice());
        product.setDescription(productRequestDTO.getDescription());
        product.setRating(0);
        product.setImageURL(productRequestDTO.getImageURL());
        return product;
    }
}
