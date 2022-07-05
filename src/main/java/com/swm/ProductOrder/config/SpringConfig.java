package com.swm.ProductOrder.config;

import com.swm.ProductOrder.controller.ConsoleController;
import com.swm.ProductOrder.util.CsvDataReader;
import com.swm.ProductOrder.util.DataReader;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public DataReader dataReader() {
        return new CsvDataReader();
    }

    @Bean
    public ConsoleController consoleController() {
        return new ConsoleController();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
