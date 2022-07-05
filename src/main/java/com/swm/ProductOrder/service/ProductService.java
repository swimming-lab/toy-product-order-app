package com.swm.ProductOrder.service;

import com.swm.ProductOrder.dto.ProductDto;
import com.swm.ProductOrder.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    /**
     * 상품 조회
     * @param productId
     * @return
     */
    public ProductDto getProduct(Long productId) {
        return modelMapper.map(
                productRepository.findById(productId).orElseThrow(),
                ProductDto.class);
    }

    /**
     * 상품 전체 조회
     * @return
     */
    public List<ProductDto> getAllProductList() {
        return Arrays.asList(modelMapper.map(
                productRepository.findAll(),
                ProductDto[].class));
    }
}
