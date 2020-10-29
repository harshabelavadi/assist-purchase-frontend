package com.example.Monitor.Repository;


import com.example.Monitor.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface MydaoRepository extends JpaRepository<Product, Integer> {

}
