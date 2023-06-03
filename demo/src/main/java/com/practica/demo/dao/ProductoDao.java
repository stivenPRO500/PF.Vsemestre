package com.practica.demo.dao;

import com.practica.demo.entity.product;
import org.springframework.data.repository.CrudRepository;

public interface ProductoDao extends CrudRepository<product, Long> {
}
