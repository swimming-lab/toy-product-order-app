package com.swm.ProductOrder.domain;

import com.swm.ProductOrder.exception.SoldOutException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    @Test
    @DisplayName("아이템 생성")
    void createProductTest() throws Exception {
        //given

        //when
        Product product = Product.createProduct(123L, "아이템1", 1000l, 10);

        //then
        assertThat(product.getId()).isEqualTo(123L);
        assertThat(product.getName()).isEqualTo("아이템1");
        assertThat(product.getPrice()).isEqualTo(1000l);
        assertThat(product.getStock()).isEqualTo(10);
    }

    @Test
    @DisplayName("재고수량 감소")
    void decreaseStockTest() throws Exception {
        //given
        Product product = Product.createProduct(123L, "아이템1", 1000l, 10);

        //when
        product.decreaseStock(10);

        //then
        assertThat(product.getStock()).isEqualTo(0);
    }

    @Test
    @DisplayName("재고수량 초과감소")
    void overDecreaseStockTest() throws Exception {
        //given
        Product product = Product.createProduct(123L, "아이템1", 1000l, 10);

        //when
        SoldOutException soldOutException = assertThrows(SoldOutException.class, () -> product.decreaseStock(100));

        //then
        assertThat(soldOutException.getMessage()).isEqualTo("SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.");
    }
}
