package com.swm.ProductOrder.repository;

import com.swm.ProductOrder.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
