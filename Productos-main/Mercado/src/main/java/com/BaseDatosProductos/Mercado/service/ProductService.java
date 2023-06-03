package com.BaseDatosProductos.Mercado.service;

import com.BaseDatosProductos.Mercado.entinty.Product;

import java.util.List;

public interface ProductService {

    public List<Product> fileAll();
    public Product findById(Long id);
    public List<Product> findByName(String name);
    public void createProducto(Product product);
    public void modifyProducto(Long id, Product product);
    public void deleteProducto(Long id);
}
