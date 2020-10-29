package com.example.Monitor;

import com.example.Monitor.Controller.MyController;
import com.example.Monitor.Model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import com.fasterxml.jackson.core.JsonParseException;

@SpringBootTest
@AutoConfigureMockMvc
class MyControllerIT {

    @Autowired
    private MyController controller;

    @Autowired
    private MockMvc mockMvc;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @BeforeEach
    void setUp() throws Exception{
        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void contextLoads() throws Exception{
        assertThat(controller).isNotNull();
    }

    @Test
    public void getAllProducts() throws Exception{
        mockMvc.perform(get("/products/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(11)));
    }

    @Test
    public void createProduct() throws Exception {
        String uri = "/products/add";
        Product product = new Product();
        product.setPid(100);
        product.setPname("IntelliVue MX400");
        product.setSize(9);
        product.setCategory("intellivue");
        product.setTouchscreen(true);
        product.setTransportMonitor(true);


        String inputJson = mapToJson(product);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void updateProduct() throws Exception {
        String uri = "/products/update/2";
        Product product = new Product();
        product.setPid(2);
        product.setPname("IntelliVue MX40");
        product.setSize(12);
        product.setCategory("effica");
        product.setTouchscreen(true);
        product.setTransportMonitor(true);



        String inputJson = mapToJson(product);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void updateProductNotFound() throws Exception {
        String uri = "/products/update/200";
        Product product = new Product();
        product.setPid(2);
        product.setPname("IntelliVue MX40");
        product.setSize(12);
        product.setCategory("effica");
        product.setTouchscreen(true);
        product.setTransportMonitor(true);

        Exception exception=assertThrows(Exception.class,()->{
            String inputJson = mapToJson(product);
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

            int status = mvcResult.getResponse().getStatus();
            assertEquals(404, status);
        });

    }

    @Test
    public void deleteProduct() throws Exception {
        String uri = "/products/delete/2";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void deleteProductNotFound() throws Exception {
        String uri = "/products/delete/200";
        Exception exception=assertThrows(Exception.class,()->{
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
            int status = mvcResult.getResponse().getStatus();
            assertEquals(404, status);
        });
    }
}