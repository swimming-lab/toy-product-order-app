package com.swm.ProductOrder.view;

import com.swm.ProductOrder.dto.OrderDto;
import com.swm.ProductOrder.dto.OrderItemDto;
import com.swm.ProductOrder.dto.ProductDto;
import com.swm.ProductOrder.util.PriceUtil;

import java.util.List;

public class SystemMessage {
    public static void message(String message) {
        System.out.print(message);
    }

    public static void error(String message) {
        System.out.println(message + "\n");
    }

    public static void startMessage() {
        message("입력(o[order]: 주문, q[quit]: 종료) : ");
    }

    public static void endMessage() {
        message("고객님의 주문 감사합니다.\n");
    }

    public static void showProductList(List<ProductDto> productList) {
        message("\n");
        System.out.printf("%s\t%s\t%s\t%s\n", "상품번호", "상품명", "판매가격", "재고수");
        for (ProductDto productDto: productList) {
            System.out.printf("%s\t%s\t%s\t%s\n",
                    productDto.getId().toString(),
                    productDto.getName(),
                    productDto.getPrice().toString(),
                    productDto.getStock().toString());
        }
        message("\n");
    }

    public static void showOrderList(OrderDto orderDto) {
        message("\n");
        message("주문 내역:\n");
        message("-------------------------------------\n");
        for (OrderItemDto orderItemDto : orderDto.getOrderItemList()) {
            System.out.printf("%s - %s개\n", orderItemDto.getName(), orderItemDto.getOrderQty());
        }
        message("-------------------------------------\n");
        System.out.printf("주문금액: %s원\n", PriceUtil.setComma(orderDto.getOrderPrice()));
        Long shipplingFee = orderDto.getTotalPrice() - orderDto.getOrderPrice();
        if (shipplingFee > 0) {
            System.out.printf("배송비: %s원\n", PriceUtil.setComma(shipplingFee));
        }
        message("-------------------------------------\n");
        System.out.printf("지불금액: %s원\n", PriceUtil.setComma(orderDto.getTotalPrice()));
        message("-------------------------------------\n");
        message("\n");
    }
}
