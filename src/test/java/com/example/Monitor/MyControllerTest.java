package com.example.Monitor;


import com.example.Monitor.Controller.MyController;
import com.example.Monitor.Model.Product;
import com.example.Monitor.Repository.MydaoRepository;
import com.example.Monitor.Service.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MyControllerTest {

    private MockMvc mockMvc;
    @Mock
    ServiceImpl service;

    @Mock
    MydaoRepository mydaoRepository;

    @Autowired
    MyController myController;

    @BeforeEach
    public void setup(){
        myController.setService(service);
        mockMvc= MockMvcBuilders.standaloneSetup(myController).build();
    }
    @Test
    public void getAllProducts() throws Exception {
        Product prod1=new Product();
        prod1.setPid(1);
        prod1.setPname("IntelliVue X3");
        prod1.setTouchscreen(true);
        prod1.setSize(15);
        prod1.setCategory("intellivue");
        prod1.setTransportMonitor(false);

        Product prod2=new Product();
        prod2.setPid(2);
        prod2.setPname("IntelliVue MX40");
        prod2.setTouchscreen(false);
        prod2.setSize(15);
        prod2.setCategory("effica");
        prod2.setTransportMonitor(true);

        List<Product> prodlist=new ArrayList<>();
        prodlist.add(prod1);
        prodlist.add(prod2);

        Mockito.when(service.getProducts()).thenReturn(prodlist);




        mockMvc.perform(get("/products/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void getProductsById() throws Exception {
        Product prod1=new Product();
        prod1.setPid(10);
        prod1.setPname("IntelliVue X3");
        prod1.setTouchscreen(true);
        prod1.setSize(15);
        prod1.setCategory("nighttime Radiant");
        prod1.setTransportMonitor(false);

        Mockito.when(mydaoRepository.findById(10)).thenReturn(java.util.Optional.of(prod1));
        Mockito.when(service.getProductsById(10)).thenReturn(java.util.Optional.of(prod1));



        mockMvc.perform((get("/products/{pid}",10)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pid", Matchers.is(10)))
                .andExpect(jsonPath("$.pname",Matchers.is("IntelliVue X3")))
                .andExpect(jsonPath("$.touchscreen",Matchers.is(true)))
                .andExpect(jsonPath("$.size",Matchers.is(15)))
                .andExpect(jsonPath("$.category",Matchers.is("nighttime Radiant")))
                .andExpect(jsonPath("$.transportMonitor",Matchers.is(false)));

    }

    public static String asJsonString(final Object obj) {

        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Test
    public void productAdd() throws Exception {
        Product prod1=new Product();
        prod1.setPid(10);
        prod1.setPname("IntelliVue X3");
        prod1.setTouchscreen(true);
        prod1.setSize(15);
        prod1.setCategory("nighttime Radiant");
        prod1.setTransportMonitor(false);

        Mockito.when(mydaoRepository.save(any(Product.class))).thenReturn(prod1);
        Mockito.when(service.addProduct(any(Product.class))).thenReturn(prod1);

        mockMvc.perform((post("/products/add")).content(asJsonString(prod1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pid", Matchers.is(10)))
                .andExpect(jsonPath("$.pname",Matchers.is("IntelliVue X3")))
                .andExpect(jsonPath("$.touchscreen",Matchers.is(true)))
                .andExpect(jsonPath("$.size",Matchers.is(15)))
                .andExpect(jsonPath("$.category",Matchers.is("nighttime Radiant")))
                .andExpect(jsonPath("$.transportMonitor",Matchers.is(false)));

    }

    @Test
    public void updateProduct() throws Exception {


        Product prod1=new Product();
        prod1.setPid(1);
        prod1.setPname("IntelliVue X3");
        prod1.setTouchscreen(true);
        prod1.setSize(15);
        prod1.setCategory("nighttime Radiant");
        prod1.setTransportMonitor(false);

        Product prod2=new Product();
        prod2.setPid(1);
        prod2.setPname("IntelliVue MX40");
        prod2.setTouchscreen(false);
        prod2.setSize(15);
        prod2.setCategory("intellivue");
        prod2.setTransportMonitor(true);

        Mockito.when(service.getProductsById(1)).thenReturn(java.util.Optional.of(prod2));
        Mockito.when(service.updateProduct(any(Product.class))).thenReturn(prod1);

        mockMvc.perform((put("/products/update/{pid}",1))
                .content(asJsonString(prod1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pid", Matchers.is(1)))
                .andExpect(jsonPath("$.pname",Matchers.is("IntelliVue X3")))
                .andExpect(jsonPath("$.touchscreen",Matchers.is(true)))
                .andExpect(jsonPath("$.size",Matchers.is(15)))
                .andExpect(jsonPath("$.category",Matchers.is("nighttime Radiant")))
                .andExpect(jsonPath("$.transportMonitor",Matchers.is(false)));
    }

    @Test
    public void DeleteProduct() throws Exception {

        Product prod1=new Product();
        prod1.setPid(10);
        prod1.setPname("IntelliVue X3");
        prod1.setTouchscreen(true);
        prod1.setSize(15);
        prod1.setCategory("nighttime Radiant");
        prod1.setTransportMonitor(false);

        //Mockito.when(mydaoRepository.save(any(Product.class))).thenReturn(prod1);
        Mockito.when(service.getProductsById(10)).thenReturn(java.util.Optional.of(prod1));
        Mockito.when(service.deleteProductById(10)).thenReturn(String.valueOf(prod1));
        mockMvc.perform((delete("/products/delete/{pid}",10)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }



}

