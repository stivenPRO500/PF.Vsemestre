package com.practica.demo.service;

import com.practica.demo.entity.product;

import java.util.List;

public interface ProductService {
    public  List<product> findAll();

    public product findById(Long id);
    public void createProduct(product product);
    public void modifyProduct(Long id, product product);


    public void deleteProduct(Long id);


}
