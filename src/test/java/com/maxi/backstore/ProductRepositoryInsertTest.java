package com.maxi.backstore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.maxi.backstore.entities.Category;
import com.maxi.backstore.entities.Product;
import com.maxi.backstore.enums.ProductStatus;
import com.maxi.backstore.repositories.CategoryRepository;
import com.maxi.backstore.repositories.ProductRepository;

@SpringBootTest
public class ProductRepositoryInsertTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void whenFindByCategory_thenReturnListProduct() {

        Category c1 = Category.builder()
                .id(null)
                .name("Books").build();

        Category c2 = Category.builder()
                .id(null)
                .name("Books relij").build();

        categoryRepository.saveAll(Arrays.asList(c1, c2));

        Product p1 = Product.builder()
                .name("Computer")
                .category(c1)
                .createAt(LocalDate.now())
                .description("Description")
                .expireAt(LocalDate.now())
                .price(Double.parseDouble("23.90"))
                .status(ProductStatus.CREATED)
                .stock(Double.parseDouble("20.00"))
                .build();

        Product p2 = Product.builder()
                .name("Computer")
                .category(c1)
                .createAt(LocalDate.now())
                .description("Description")
                .expireAt(LocalDate.now())
                .price(Double.parseDouble("23.90"))
                .status(ProductStatus.CREATED)
                .stock(Double.parseDouble("20.00"))
                .build();

        productRepository.saveAll(Arrays.asList(p1, p2));

        List<Product> list = productRepository.findByCategory(p1.getCategory());
        assertEquals(list.size(), 2);
    }

}
