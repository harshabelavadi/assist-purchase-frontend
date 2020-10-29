package com.example.Monitor.Service;

import com.example.Monitor.Repository.MydaoRepository;
import com.example.Monitor.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@org.springframework.stereotype.Service
public class ServiceImpl implements IService {
    List<Product> touchscreenList;
    List<Product> sizeList;
    List<Product> categoryList;
    List<Product> transportMonitorList;

    MydaoRepository dao;
    @Autowired
    public void setdao(MydaoRepository dao)
    {
        this.dao = dao;
    }

    /*
    Returns list of all the products in thr repository
     */
    @Override
    public List<Product> getProducts() {
        return dao.findAll();
    }


    public Iterable<Product> save(List<Product> products) {
        return dao.saveAll(products);
    }


    /*
    Returns list of products that have touchscreen, and focuses on no other feature of the product
     */
    @Override
    public void getProductsTouchScreen(boolean touchscreen){
		touchscreenList=new ArrayList<>();
        List<Product> lprod=getProducts();
        for(int i=0;i<lprod.size();i++)
        {
            if(lprod.get(i).isTouchscreen()==touchscreen) {
                touchscreenList.add(lprod.get(i));
            }
        }
    }


    /*
    Returns list of products that have size according to user argument
     */
    @Override
    public void getProductsSize(int size){
		sizeList=new ArrayList<>();
        List<Product> lprod=getProducts();
        for(int i=0;i<lprod.size();i++)
        {
            if(lprod.get(i).getSize()==size)
                sizeList.add(lprod.get(i));
        }
    }


    /*
    Returns list of products that fall into category according to user argument
     */
    @Override
    public void getProductsCategory(String category){
		categoryList=new ArrayList<>();
        List<Product> lprod=getProducts();
        for(int i=0;i<lprod.size();i++)
        {
            if(lprod.get(i).getCategory().equals(category))
                categoryList.add(lprod.get(i));
        }
    }


    /*
    Returns list of products that have transportMonitor as true i.e. the are portable
     */
    @Override
    public void getProductsTransportMonitor(boolean transportMonitor){
        transportMonitorList=new ArrayList<>();
		List<Product> lprod=getProducts();
        for(int i=0;i<lprod.size();i++)
        {
            if(lprod.get(i).isTransportMonitor()==transportMonitor)
                transportMonitorList.add(lprod.get(i));
        }
    }

    /*
   Creates two sets for two lists each and finally performs intersection operation on them
     */
    public Set<Product> getProductSpecs(){
        Set<Product> intersectionSet1=categoryList.stream().distinct().filter(transportMonitorList::contains)
                .collect(Collectors.toSet());
        Set<Product> intersectionSet2=touchscreenList.stream().distinct().filter(sizeList::contains)
                .collect(Collectors.toSet());
        intersectionSet1.retainAll(intersectionSet2);
        return intersectionSet1;
    }


    /*
    Converts the set created by interscetion to be converted to arraylist
     */
    @Override
    public List<Product> getProductsAccParameters(boolean touchscreen, int size, String category, boolean transportMonitor) {
        getProductsCategory(category);
        getProductsTransportMonitor(transportMonitor);
        getProductsSize(size);
        getProductsTouchScreen(touchscreen);
        List<Product> userRequestedProd=new ArrayList<>(getProductSpecs());
        return userRequestedProd;
    }

    @Override
    public Optional<Product> getProductsById(int pid) {
        return dao.findById(pid);
    }

    @Override
    public Product addProduct(Product b) {
        return dao.save(b);
    }

    @Override
    public Product updateProduct(Product b) {
        return dao.save(b);
    }

    @Override
    public String deleteProductById(int pid) {
        dao.deleteById(pid);
        return("Product is deleted successsfully");
    }
}
