package com.swm.ProductOrder.service;

import com.swm.ProductOrder.domain.Order;
import com.swm.ProductOrder.domain.OrderItem;
import com.swm.ProductOrder.domain.Product;
import com.swm.ProductOrder.dto.CartItemDto;
import com.swm.ProductOrder.dto.OrderDto;
import com.swm.ProductOrder.exception.SoldOutException;
import com.swm.ProductOrder.repository.OrderRepository;
import com.swm.ProductOrder.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final Long SHIPPING_FEE = 2500L;
    private final Long FREE_ORDER_FEE = 50000L;

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    /**
     * 주문서 생성
     * @param cartItemDtoList
     * @return ㅒ
     */
    @Transactional
    public OrderDto createOrder(List<CartItemDto> cartItemDtoList) {
        Long orderPrice = 0L;
        Long totalPrice = 0L;

        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartItemDto cartItemDto : cartItemDtoList) {
            // 데이터 원자성(Atomicity) > JPA Pessimistic Lock으로 해결
            Product product = productRepository.findById(cartItemDto.getProductId()).get();
            if (product.getStock() < cartItemDto.getOrderQty()) {
                throw new SoldOutException();
            } else {
                product.setStock(product.getStock() - cartItemDto.getOrderQty());
            }

            OrderItem orderItem = OrderItem.createOrderItem(
                    product,
                    product.getPrice(),
                    cartItemDto.getOrderQty());
            orderItemList.add(orderItem);

            orderPrice += product.getPrice() * orderItem.getOrderQty();
        }

        totalPrice = getTotalPrice(orderPrice);

        Order order = Order.createOrder(orderPrice, totalPrice, orderItemList);
        orderRepository.save(order);

        return OrderDto.from(order);
    }

    /**
     * 최종 지불 금액 계산(배송비 체크)
     * @param orderPrice
     * @return
     */
    private Long getTotalPrice(Long orderPrice) {
        return orderPrice < FREE_ORDER_FEE ? orderPrice + SHIPPING_FEE : orderPrice;
    }
}
