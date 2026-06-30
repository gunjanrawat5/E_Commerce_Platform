package dev.gunjan.EcommerceProduct.service;

import dev.gunjan.EcommerceProduct.dto.CategoryResponseDTO;
import dev.gunjan.EcommerceProduct.dto.CreateCategoryRequestDTO;
import dev.gunjan.EcommerceProduct.dto.CreateProductRequestDTO;
import dev.gunjan.EcommerceProduct.entity.Category;
import dev.gunjan.EcommerceProduct.entity.Product;
import dev.gunjan.EcommerceProduct.exception.CategoryNotFoundException;
import dev.gunjan.EcommerceProduct.mapper.CategoryEntityDTOMapper;
import dev.gunjan.EcommerceProduct.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO getCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category with given id not found")
        );
        return CategoryEntityDTOMapper.convertCategoryToCategoryResponseDTO(category);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        for(Category category : categories){
            categoryResponseDTOS.add(CategoryEntityDTOMapper.convertCategoryToCategoryResponseDTO(category));
        }
        return  categoryResponseDTOS;
    }

    @Override
    public CategoryResponseDTO createCategory(CreateCategoryRequestDTO createCategoryRequestDTO) {

        return null;

    }

    @Override
    public CategoryResponseDTO updateCategory(CreateCategoryRequestDTO createCategoryRequestDTO, UUID categoryId) {
        return null;
    }


    @Override
    public boolean deleteCategory(UUID categoryId) {
        categoryRepository.deleteById(categoryId);
        return true;
    }

    @Override
    public double getTotalPriceForCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category for given id is not found")
        );
        if(category.getProducts().isEmpty()){
            return 0;
        }
        else{
            double sum  = 0;
            for(Product p : category.getProducts()){
                sum = sum + p.getPrice();
            }
            return sum;
        }
    }
}
