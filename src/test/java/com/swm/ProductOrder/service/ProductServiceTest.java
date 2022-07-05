package com.swm.ProductOrder.service;

import com.swm.ProductOrder.dto.ProductDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void getProduct() {
        Long productId = 377169L;
        ProductDto productDto = productService.getProduct(productId);

        assertEquals(productDto.getId(), 377169L);
    }

    @Test
    void getProductWithException() {
        Long productId = 0000000L;

        assertThrows(NoSuchElementException.class, () -> productService.getProduct(productId));
    }

    @Test
    void getAllProductList() {
        int size = productService.getAllProductList().size();
        assertEquals(size, 19);
    }
}