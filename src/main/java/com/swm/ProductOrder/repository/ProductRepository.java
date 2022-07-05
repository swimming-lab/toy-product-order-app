package com.swm.ProductOrder.repository;

import com.swm.ProductOrder.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 동시성 재고 해결을 위한 Pessimistic Lock 설정
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findById(Long id);
}
