package com.elorrieta.storeapi.service;

import com.elorrieta.storeapi.model.Product;
import com.elorrieta.storeapi.dto.ProductDTO;


import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> findAll();
    ProductDTO findById(Long id);
    ProductDTO save(ProductDTO productDto);
    ProductDTO update(Long id, ProductDTO productDto);
    void delete(Long id);
}
