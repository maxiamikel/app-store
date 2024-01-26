package com.maxi.backstore.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maxi.backstore.entities.Product;
import com.maxi.backstore.services.product.ProductServiceImplementation;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductServiceImplementation productService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> productsList = new ArrayList<>();
        productsList = productService.findAllProducts();

        return ResponseEntity.ok().body(productsList);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productService.createProduct(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newProduct.getId())
                .toUri();

        return ResponseEntity.created(uri).body(newProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findPdoructById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(productService.findPdoructById(id));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Product>> findProductByCategory(
            @RequestParam(name = "category", required = false) Long categoryId) {
        List<Product> productsList = new ArrayList<>();
        if (categoryId == null) {
            productsList = productService.findAllProducts();
            return ResponseEntity.ok().body(productsList);
        }
        return ResponseEntity.ok().body(productService.findProductByCategory(categoryId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok().body(productService.updateProduct(id, product));
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long id,
            @RequestParam(name = "quantity", required = true) Double quantity) {
        return ResponseEntity.ok().body(productService.updateStock(id, quantity));
    }

    @PutMapping("/stock/{id}")
    public ResponseEntity<Product> substractStock(@PathVariable Long id,
            @RequestParam(name = "quantity", required = true) Double quantity) {
        return ResponseEntity.ok().body(productService.substractStock(id, quantity));
    }
}
