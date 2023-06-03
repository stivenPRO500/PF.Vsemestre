package com.BaseDatosProductos.Mercado.controllers;

import com.BaseDatosProductos.Mercado.entinty.Product;
import com.BaseDatosProductos.Mercado.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
@RequestMapping("/catalogoProductos")
public class ProductControllers {
    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> listarProductos(){
        return productService.fileAll();
    }
    @GetMapping(value = "/{id}")
    public Product obtenerProducto(@RequestParam Long id){
        return productService.findById(id);
    }
    @PostMapping()
    public void crearProducto(@RequestBody Product product){
        productService.createProducto(product);
    }
    @PutMapping(value = "/{id}")
    public void modificarProducto(@PathVariable Long id, @RequestBody Product product){
        productService.modifyProducto(id, product);
    }
    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProducto(id);
    }
    @GetMapping(value = "/{name}")
    public List<Product> searchName(@RequestParam String name) {
        return productService.findByName(name);
    }
}
