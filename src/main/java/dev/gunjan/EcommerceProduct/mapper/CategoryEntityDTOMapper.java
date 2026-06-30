package dev.gunjan.EcommerceProduct.mapper;

import dev.gunjan.EcommerceProduct.dto.CategoryResponseDTO;
import dev.gunjan.EcommerceProduct.dto.CreateCategoryRequestDTO;
import dev.gunjan.EcommerceProduct.dto.ProductResponseDTO;
import dev.gunjan.EcommerceProduct.entity.Category;
import dev.gunjan.EcommerceProduct.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryEntityDTOMapper {

    public static CategoryResponseDTO convertCategoryToCategoryResponseDTO(Category category){
        // call product entity to proudct response dto mapper
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategoryName(category.getName());
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        if(!(category.getProducts() == null || category.getProducts().isEmpty())) {
            for (Product product : category.getProducts()) {
                productResponseDTOS.add(ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product));
            }
        }
        categoryResponseDTO.setProducts(productResponseDTOS);
        return  categoryResponseDTO;
    }

    public static Category ConvertCreateCategoryRequestDTOToCategory(CreateCategoryRequestDTO createCategoryRequestDTO){
        Category category = new Category();
        category.setName(createCategoryRequestDTO.getCategoryName());
        return category;
    }
}
