package com.BaseDatosProductos.Mercado.service;

import com.BaseDatosProductos.Mercado.dao.ProductDao;
import com.BaseDatosProductos.Mercado.entinty.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> fileAll() {
        return (List<Product>)productDao.findAll();
    }

    @Override
    public Product findById(Long id) {

        return productDao.findById(id).orElse(null);
    }

    @Override
    public List<Product> findByName(String name) {
        return productDao.findByName(name);
    }

    @Override
    public void createProducto(Product product) {
        productDao.save(product);
    }

    @Override
    public void modifyProducto(Long id, Product product) {
        if(productDao.existsById(id)){
            product.setId(id);
            productDao.save(product);
        }
    }

    @Override
    public void deleteProducto(Long id) {
        if(productDao.existsById(id)){
            productDao.deleteById(id);
        }
    }
}
