package com.example.Monitor;

import com.example.Monitor.Model.Product;
import com.example.Monitor.Service.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class MonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorApplication.class, args);
	}

    @Bean
    CommandLineRunner runner(ServiceImpl service) {
        return args -> {
            // read json and write to db
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/products.json");
            try {
                List<Product> products = mapper.readValue(inputStream,typeReference);
                service.save(products);
                System.out.println("Products Saved!");
            } catch (IOException e){
                System.out.println("Unable to save Products: " + e.getMessage());
            }
        };
    }

}
