package com.BaseDatosProductos.Mercado.dao;

import com.BaseDatosProductos.Mercado.entinty.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDao extends CrudRepository<Product, Long> {

    List<Product> findByName(String name);

}
