package dev.gunjan.EcommerceProduct.mapper;

import dev.gunjan.EcommerceProduct.dto.CategoryResponseDTO;
import dev.gunjan.EcommerceProduct.dto.CreateCategoryRequestDTO;
import dev.gunjan.EcommerceProduct.entity.Category;

public class CategoryEntityDTOMapper {

    public static CategoryResponseDTO convertCategoryToCategoryResponseDTO(Category category){
        // call product entity to proudct response dto mapper
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategoryId(category.getId());
        categoryResponseDTO.setCategoryName(category.getName());
        categoryResponseDTO.setProducts();
        return  categoryResponseDTO;
    }

    public static Category ConvertCreateCategoryRequestDTOToCategory(CreateCategoryRequestDTO createCategoryRequestDTO){
        Category category = new Category();
        category.setName(createCategoryRequestDTO.getCategoryName());
        return category;
    }
}
