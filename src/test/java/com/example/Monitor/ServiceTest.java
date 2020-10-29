package com.example.Monitor;
import com.example.Monitor.Model.Product;
import com.example.Monitor.Repository.MydaoRepository;
import com.example.Monitor.Service.ServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ServiceTest {

    @Mock
    MydaoRepository dao;

    @Autowired
    ServiceImpl service;
    @BeforeEach
    public void setup(){
        service.setdao(dao);

    }

    @Test
    public void getallProductsTest(){
        Product prod1=new Product();
        prod1.setPid(10);
        prod1.setPname("IntelliVue X3");
        prod1.setTouchscreen(true);
        prod1.setSize(15);
        prod1.setCategory("intellivue");
        prod1.setTransportMonitor(false);

        Product prod2=new Product();
        prod2.setPid(20);
        prod2.setPname("IntelliVue MX40");
        prod2.setTouchscreen(false);
        prod2.setSize(15);
        prod2.setCategory("intellivue");
        prod2.setTransportMonitor(true);

        List<Product> prodlist=new ArrayList<>();
        prodlist.add(prod1);
        prodlist.add(prod2);

        Mockito.when(dao.findAll()).thenReturn(prodlist);

        assertEquals(service.getProducts(),prodlist);

    }

    @Test
    public void saveProducts(){
        Product prod1=new Product();
        prod1.setPid(10);
        prod1.setPname("IntelliVue X3");
        prod1.setTouchscreen(true);
        prod1.setSize(15);
        prod1.setCategory("intellivue");
        prod1.setTransportMonitor(false);

        Product prod2=new Product();
        prod2.setPid(20);
        prod2.setPname("IntelliVue MX40");
        prod2.setTouchscreen(false);
        prod2.setSize(15);
        prod2.setCategory("intellivue");
        prod2.setTransportMonitor(true);

        List<Product> prodlist=new ArrayList<>();
        prodlist.add(prod1);
        prodlist.add(prod2);

        Mockito.when(dao.saveAll(prodlist)).thenReturn(prodlist);
        assertEquals(prodlist, service.save(prodlist));

    }

    @Test
    public void ReturnUserSpecProducts(){
        Product prod1=new Product();
        prod1.setPid(10);
        prod1.setPname("IntelliVue X3");
        prod1.setTouchscreen(true);
        prod1.setSize(15);
        prod1.setCategory("effica");
        prod1.setTransportMonitor(false);



        List<Product> prodlist=new ArrayList<>();
        prodlist.add(prod1);


        Mockito.when(dao.findAll()).thenReturn(prodlist);//the list returned contains only the prod1 object
        //the only method needed to be mocked is the findAll() method since it is used to get the products list inside the function
        assertEquals(service.getProductsAccParameters(true,15,"effica",false),prodlist);
    }

    @Test
    public void getProductsById(){
        Product prod1=new Product();
        prod1.setPid(10);
        prod1.setPname("IntelliVue X3");
        prod1.setTouchscreen(true);
        prod1.setSize(15);
        prod1.setCategory("intellivue");
        prod1.setTransportMonitor(false);

        Mockito.when(dao.findById(10)).thenReturn(java.util.Optional.of(prod1));
        assertEquals(service.getProductsById(10),java.util.Optional.of(prod1));

    }

    @Test
    public void addProductReturnsSuccess(){
        Product prod1=new Product();
        prod1.setPid(10);
        prod1.setPname("IntelliVue X3");
        prod1.setTouchscreen(true);
        prod1.setSize(15);
        prod1.setCategory("intellivue");
        prod1.setTransportMonitor(false);


        Mockito.when(dao.save(any(Product.class))).thenReturn(prod1);
        assertEquals(service.addProduct(prod1),prod1);
    }

    @Test
    public void updateProductReturnsSuccess(){
        Product prod1=new Product();
        prod1.setPid(10);
        prod1.setPname("IntelliVue X3");
        prod1.setTouchscreen(true);
        prod1.setSize(15);
        prod1.setCategory("intellivue");
        prod1.setTransportMonitor(false);

        Mockito.when(dao.save(any(Product.class))).thenReturn(prod1);
        assertEquals(service.updateProduct(prod1),prod1);
    }

    @Test
    public void deleteProductByIdReturnsSuccess(){
        Product prod1=new Product();
        prod1.setPid(10);
        prod1.setPname("IntelliVue X3");
        prod1.setTouchscreen(true);
        prod1.setSize(15);
        prod1.setCategory("intellivue");
        prod1.setTransportMonitor(false);

        assertEquals(service.deleteProductById(10),"Product is deleted successsfully");
    }

}
