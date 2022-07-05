package com.swm.ProductOrder.dto;

import com.swm.ProductOrder.domain.Order;
import com.swm.ProductOrder.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long orderPrice;
    private Long totalPrice;
    private List<OrderItemDto> orderItemList = new ArrayList<>();

    public static OrderDto from(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.id = order.getId();
        orderDto.orderPrice = order.getOrderPrice();
        orderDto.totalPrice = order.getTotalPrice();
        for (OrderItem orderItem : order.getOrderItemList()) {
            orderDto.orderItemList.add(
                    new OrderItemDto(orderItem.getProduct(),
                    orderItem.getOrderQty()));
        }

        return orderDto;
    }
}
