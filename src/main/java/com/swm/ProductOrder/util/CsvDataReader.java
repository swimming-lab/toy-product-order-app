package com.swm.ProductOrder.util;

import com.opencsv.CSVReader;
import com.swm.ProductOrder.domain.Product;
import com.swm.ProductOrder.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;

public class CsvDataReader implements DataReader{

    private final String FILE_NAME = "items_.csv";

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void init() throws IOException {
        read();
    }

    /**
     * CSV 데이터 읽고 등록하기
     * @throws IOException
     */
    @Override
    public void read() throws IOException {
        CSVReader reader = new CSVReader(new FileReader(FILE_NAME));
        reader.skip(1);

        for (String[] line : reader) {
            if (line.length < 4) {
                new Exception("csv 파일 확인 필요");
            } else {
                Product product = Product.createProduct(
                                Long.parseLong(line[0]),
                                line[1],
                                Long.parseLong(line[2]),
                                Integer.parseInt(line[3]));

                productRepository.save(product);
            }
        }
    }
}
