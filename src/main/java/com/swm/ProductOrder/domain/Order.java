package com.swm.ProductOrder.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_price")
    private Long orderPrice;

    @Column(name = "total_price")
    private Long totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    public static Order createOrder(Long orderPrice, Long totalPrice, List<OrderItem> orderItemList) {
        Order order = new Order();
        order.orderPrice = orderPrice;
        order.totalPrice = totalPrice;

        for (OrderItem orderItem : orderItemList) {
            order.orderItemList.add(orderItem);
            orderItem.setOrder(order);
        }

        return order;
    }
}
