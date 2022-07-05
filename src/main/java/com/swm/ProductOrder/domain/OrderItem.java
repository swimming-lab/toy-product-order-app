package com.swm.ProductOrder.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "order_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "order_price")
    private Long orderPrice;

    @Column(name = "order_qty")
    private Integer orderQty;

    public static OrderItem createOrderItem(Product product, Long orderPrice, Integer orderQty) {
        OrderItem orderItem = new OrderItem();
        orderItem.product = product;
        orderItem.orderPrice = orderPrice;
        orderItem.orderQty = orderQty;

        return orderItem;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
