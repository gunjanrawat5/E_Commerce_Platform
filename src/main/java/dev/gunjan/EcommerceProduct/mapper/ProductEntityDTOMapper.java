package dev.gunjan.EcommerceProduct.mapper;

import dev.gunjan.EcommerceProduct.dto.ProductResponseDTO;
import dev.gunjan.EcommerceProduct.entity.Product;

public class ProductEntityDTOMapper {
    public static ProductResponseDTO convertProductEntityToProductResponseDTO(Product product){
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        //responseDTO.setProductId(product.getId());
        responseDTO.setTitle(product.getTitle());
        responseDTO.setCategory(product.getCategory());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setRating(product.getRating());
        responseDTO.setImageURL(product.getImageURL());
        return responseDTO;

    }
}
