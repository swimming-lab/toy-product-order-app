package com.swm.ProductOrder.util;

import com.swm.ProductOrder.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class CsvDataReaderTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DataReader dataReader;

    @Test
    public void readTest() throws IOException {
        dataReader.read();
        Assertions.assertEquals(productRepository.findAll().size(), 19);
    }
}