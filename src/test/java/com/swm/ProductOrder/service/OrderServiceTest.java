package com.swm.ProductOrder.service;

import com.swm.ProductOrder.dto.CartItemDto;
import com.swm.ProductOrder.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @Test
    public void createOrderTest() {
        Long productId = 377169L;   // 재고 60개
        Integer orderQty = 1;
        int expectedStock = 50;

        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        cartItemDtoList.add(CartItemDto.builder()
                .productId(productId)
                .orderQty(orderQty)
                .build());

        for (int i=0; i<10; i++) {
            System.out.println(i + " - 시작");
            orderService.createOrder(cartItemDtoList);
            ProductDto productDto = productService.getProduct(productId);
            System.out.println(productDto.getStock());
            System.out.println(i + " - 끝");
        }

        ProductDto productDto3 = productService.getProduct(productId);
        assertEquals(expectedStock, productDto3.getStock());
    }

    @Test
    public void createOrderWithConcurrencyTest() throws InterruptedException {
        Long productId = 377169L;   // 재고 60개
        Integer orderQty = 1;
        int expectedStock = 50;

        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        cartItemDtoList.add(CartItemDto.builder()
                .productId(productId)
                .orderQty(orderQty)
                .build());

        int numberOfThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(20);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i=0; i<numberOfThreads; i++) {
            int threadId = i;
            service.execute(() -> {
                System.out.println(threadId + " - 시작");
                orderService.createOrder(cartItemDtoList);
                System.out.println(threadId + " - " + productService.getProduct(productId).getStock());
                System.out.println(threadId + " - 끝");
                latch.countDown();
            });
        }

        latch.await();

        ProductDto productDto = productService.getProduct(productId);
        assertEquals(expectedStock, productDto.getStock());
    }
}