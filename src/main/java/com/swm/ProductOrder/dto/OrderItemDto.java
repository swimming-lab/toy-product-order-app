package com.swm.ProductOrder.dto;

import com.swm.ProductOrder.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private String name;
    private Integer orderQty;

    public OrderItemDto(Product product, Integer orderQty) {
        this.name = product.getName();
        this.orderQty = orderQty;
    }
}
