package com.swm.ProductOrder.domain;

import com.swm.ProductOrder.exception.SoldOutException;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "stock")
    private Integer stock;

    public static Product createProduct(Long id, String name, Long price, Integer stock) {
        Product product = new Product();
        product.id = id;
        product.name = name;
        product.price = price;
        product.stock = stock;

        return product;
    }

    public void decreaseStock(int quantity) {
        int restStock = this.stock - quantity;
        if (restStock < 0) {
            throw new SoldOutException();
        }
        this.stock = restStock;
    }
}
