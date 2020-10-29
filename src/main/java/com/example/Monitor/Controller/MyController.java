package com.example.Monitor.Controller;
import com.example.Monitor.Model.Product;
import com.example.Monitor.Service.ServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/products")
public class MyController {

    ServiceImpl service;

    @Autowired
    public void setService(ServiceImpl service)
    {
        this.service = service;
    }

    @RequestMapping(value="/all",method=RequestMethod.GET)
    public List<Product> getAllProducts(){
        return service.getProducts();

    }


    /*
    This Api returns the list of products  according to user specifications
     */
    @RequestMapping(method=RequestMethod.GET)
    public List<Product> getProductUserSpec(@RequestParam(value="touchscreen") boolean touchscreen, @RequestParam(value="size") int size, @RequestParam(value="category") String category, @RequestParam(value="transportMonitor") boolean transportMonitor) throws Exception{
        return service.getProductsAccParameters(touchscreen,size,category,transportMonitor);


    }


    @RequestMapping(value= "/{pid}", method= RequestMethod.GET)
    public Product getProdById(@PathVariable int pid)throws Exception
    {
        Optional<Product> b =  service.getProductsById(pid);
        return b.get();
    }

    @RequestMapping(value= "/add", method= RequestMethod.POST)
    public Product addProductToList(@RequestBody Product product) {

        return service.addProduct(product);
    }

    @RequestMapping(value="/update/{pid}",method= RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product, @PathVariable int pid ) throws Exception {

        Optional<Product> b =  service.getProductsById(pid);
        if(!b.isPresent())
            throw new Exception("Product is not present in the library");
        // if(product.getCategory()==null || product.getCategory().isEmpty())
        //    product.setCategory(b.get().getCategory());


        product.setPid(pid);
        return service.updateProduct(product);
    }

    @RequestMapping(value="/delete/{pid}",method=RequestMethod.DELETE)
    public void deleteBookByISBN(@PathVariable int pid) throws Exception {
        Optional<Product> b=service.getProductsById(pid);
        if(!b.isPresent())
            throw new Exception("Product is not present in the library");
        service.deleteProductById(pid);
    }

}
