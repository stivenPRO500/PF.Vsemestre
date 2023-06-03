package com.practica.demo.controller;

import com.practica.demo.entity.product;
import com.practica.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public List<product> listarProductos(){
        return productService.findAll();
    }
    @GetMapping(value = "/{id}")
    public product obtenerProducto(@PathVariable Long id){
        return productService.findById(id);
    }

    @PostMapping()
    public void createProducto(@RequestBody product product){
        productService.createProduct(product);
    }

    @PutMapping(value = "/{id}")
    public void modificarProducto(@RequestBody product product){
        productService.createProduct(product);

    }
    @DeleteMapping(value = "/{id}")
    public void modificarProducto(@PathVariable Long id){
        productService.deleteProduct(id);
    }

}
