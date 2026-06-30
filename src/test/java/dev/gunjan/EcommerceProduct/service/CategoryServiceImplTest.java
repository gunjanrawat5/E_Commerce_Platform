package dev.gunjan.EcommerceProduct.service;

import dev.gunjan.EcommerceProduct.entity.Category;
import dev.gunjan.EcommerceProduct.entity.Product;
import dev.gunjan.EcommerceProduct.exception.CategoryNotFoundException;
import dev.gunjan.EcommerceProduct.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CategoryServiceImplTest {

    // all dependencies
    @Mock
    private CategoryRepository categoryRepository;

    // the actual class that we are testing
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this); // not req nowadays, was used to initalise and adds all the req mocks

    }

    @Test
    public void testGetTotalPriceForMultipleProductsUnderCategory(){
        UUID categoryId = UUID.randomUUID();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(getCateogryMockData());
        double actualTotalCost = categoryService.getTotalPriceForCategory(categoryId);
        double expectedTotalCost  = 300.00;
        Assertions.assertEquals(expectedTotalCost, actualTotalCost);

    }
    @Test
    public void testGetTotalPriceForZeroProductsUnderCategory() {
        // Arrange
        UUID categoryId = UUID.randomUUID();
        Optional<Category> categoryOptionalMockData = getCategoryMockDataWithZeroProducts();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(categoryOptionalMockData);
        double actualCost = categoryService.getTotalPriceForCategory(categoryId);
        //Act
        double expectedCost = 0.0;
        //Assert
        Assertions.assertEquals(expectedCost , actualCost);
        Mockito.verify(categoryRepository.findById(categoryId));
    }

    @Test
    public void testCategoryNotFoundExceptionThrown(){
        // arrange
        UUID categoryId = UUID.randomUUID();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // act and assert
         Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryService.getTotalPriceForCategory(categoryId) );
    }

    private Optional<Category> getCategoryMockDataWithZeroProducts(){
        UUID randomCategoryId = UUID.randomUUID();
        Category category = new Category();
        category.setId(randomCategoryId);
        category.setName("CategoryName");

        List<Product> products = new ArrayList<>();
        category.setProducts(products);
        return  Optional.of(category);
    }



    public Optional<Category> getCateogryMockData(){
        UUID randomCategoryId = UUID.randomUUID();
        Category category = new Category();
        category.setId(randomCategoryId);
        category.setName("CategoryName");

        Product product1 = new Product();
        product1.setId(UUID.randomUUID());
        product1.setTitle("P1");
        product1.setPrice(100.00);
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(UUID.randomUUID());
        product2.setTitle("P2");
        product2.setPrice(200.00);
        product2.setCategory(category);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        category.setProducts(products);
        return  Optional.of(category);
    }
}
