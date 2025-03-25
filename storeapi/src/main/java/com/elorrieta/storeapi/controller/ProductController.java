
package com.elorrieta.storeapi.controller;

import com.elorrieta.storeapi.model.Product;
import com.elorrieta.storeapi.dto.ProductDTO;
import com.elorrieta.storeapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.elorrieta.storeapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

   // @Autowired not needed if the constructor holds  a single parameter
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
    	  List<ProductDTO> products = productService.findAll()
    		        .stream()
    		        .map(p -> new ProductDTO(
    	                    p.getProductId(),
    	                    p.getAmount(),
    	                    p.getCost(),
    	                    p.getEnabled(),
    	                    p.getImage(),
    	                    p.getMinimumAmount(),
    	                    p.getName(),
    	                    p.getRetailPrice(),
    	                    p.getSeason()
    	                ))
    	                .toList();
    	  return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        return product.map(p -> ResponseEntity.ok(new ProductDTO(
                    p.getProductId(),
                    p.getAmount(),
                    p.getCost(),
                    p.getEnabled(),
                    p.getImage(),
                    p.getMinimumAmount(),
                    p.getName(),
                    p.getRetailPrice(),
                    p.getSeason()
                )))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existingProduct = productService.findById(id);
        if (existingProduct.isPresent()) {
            product.setProductId(id); // ensure the ID stays the same
            Product updated = productService.save(product);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.findById(id).isPresent()) {
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

