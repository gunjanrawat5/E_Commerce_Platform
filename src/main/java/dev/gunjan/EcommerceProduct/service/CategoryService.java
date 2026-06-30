package dev.gunjan.EcommerceProduct.service;

import dev.gunjan.EcommerceProduct.dto.CategoryResponseDTO;
import dev.gunjan.EcommerceProduct.dto.CreateCategoryRequestDTO;
import dev.gunjan.EcommerceProduct.dto.CreateProductRequestDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryResponseDTO getCategory(UUID categoryId);
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO createCategory(CreateCategoryRequestDTO createCategoryRequestDTO);
    CategoryResponseDTO updateCategory(CreateCategoryRequestDTO createCategoryRequestDTO, UUID categoryId);
    boolean deleteCategory(UUID categoryId);
    double getTotalPriceForCategory(UUID categoryId);
}
