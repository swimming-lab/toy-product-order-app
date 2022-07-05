package com.swm.ProductOrder.exception;

public class SoldOutException extends RuntimeException {
    public SoldOutException() {
        super("SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.");
    }
}
