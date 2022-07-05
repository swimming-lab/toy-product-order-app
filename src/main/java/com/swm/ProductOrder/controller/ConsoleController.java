package com.swm.ProductOrder.controller;

import com.swm.ProductOrder.dto.CartItemDto;
import com.swm.ProductOrder.dto.OrderDto;
import com.swm.ProductOrder.exception.SoldOutException;
import com.swm.ProductOrder.service.OrderService;
import com.swm.ProductOrder.service.ProductService;
import com.swm.ProductOrder.view.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ConsoleController {

    private Scanner sc = new Scanner(System.in);
    private boolean isRun = true;
    private List<CartItemDto> cartItemDtoList;

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    /**
     * 콘솔 시작
     */
    public void run() {
        while (isRun) {
            SystemMessage.startMessage();
            String input = sc.nextLine().toLowerCase(Locale.ROOT);

            try {
                switch (input) {
                    case "o":
                    case "order":
                        preOrder();
                        requestOrder();
                        break;
                    case "q":
                    case "quit":
                        isRun = false;
                        break;
                    default:
                        SystemMessage.message("잘못 입력했습니다.\n");
                        break;
                }
            } catch(SoldOutException e) {
                SystemMessage.error(e.getMessage());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        SystemMessage.endMessage();
    }

    /**
     * 주문 받기
     */
    private void requestOrder() {
        while (true) {
            SystemMessage.message("상품번호: ");
            String productId = sc.nextLine().trim();
            if (productId.isEmpty()) {
                break;
            }

            SystemMessage.message("수량: ");
            String qty = sc.nextLine().trim();
            if (qty.isEmpty()) {
                SystemMessage.message("수량을 입력해주세요.\n");
                continue;
            }

            addToCart(Long.parseLong(productId), Integer.parseInt(qty));
        }

        if (getCartItemList().size() > 0) {
            OrderDto orderDto = orderService.createOrder(getCartItemList());
            SystemMessage.showOrderList(orderDto);
        } else {
            SystemMessage.message("카트에 담긴 상품이 없습니다.\n");
        }
    }

    /**
     * 주문 받기전 준비
     */
    private void preOrder() {
        SystemMessage.showProductList(productService.getAllProductList());
        cartItemDtoList = new ArrayList<>();
    }

    /**
     * 장바구니 담기
     * @param productId
     * @param orderQty
     */
    private void addToCart(Long productId, int orderQty) {
        try {
            productService.getProduct(productId);
            cartItemDtoList.add(CartItemDto.builder()
                    .productId(productId)
                    .orderQty(orderQty)
                    .build());
        } catch (NoSuchElementException e) {
            SystemMessage.message("상품번호를 잘못 입력했습니다.\n");
        }
    }

    /**
     * 장바구니 아이템 가져오기
     * @return
     */
    private List<CartItemDto> getCartItemList() {
        return cartItemDtoList;
    }
}
