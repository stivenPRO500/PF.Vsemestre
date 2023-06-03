package com.practica.demo.service;

import com.practica.demo.dao.ProductoDao;
import com.practica.demo.entity.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoServiceImp implements ProductService{
    @Autowired
    private ProductoDao productoDao;
    private Long id;
    private com.practica.demo.entity.product product;

    @Override
    public List<product> findAll() {
        return (List<product>) productoDao.findAll();
    }

    @Override
    public product findById(Long id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    public void createProduct(com.practica.demo.entity.product product) {

    }

    @Override
    public void modifyProduct(Long id, com.practica.demo.entity.product product) {

    }

    @Override
    public void deleteProduct(Long id) {

    }


}
