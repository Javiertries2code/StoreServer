package com.elorrieta.storeapi.service;

import com.elorrieta.storeapi.dto.ProductDTO;
import com.elorrieta.storeapi.model.Product;
import com.elorrieta.storeapi.repository.ProductRepository;
import com.elorrieta.storeapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private ProductDTO convertToDto(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setImage(product.getImage());
        dto.setName(product.getName());
        dto.setAmount(product.getAmount());
        dto.setMinimumAmount(product.getMinimumAmount());
        dto.setSeason(product.getSeason());
        dto.setEnabled(product.getEnabled());
        dto.setCost(product.getCost());
        dto.setRetailPrice(product.getRetailPrice());
        return dto;
    }

    private Product convertToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setProductId(dto.getProductId());
        product.setImage(dto.getImage());
        product.setName(dto.getName());
        product.setAmount(dto.getAmount());
        product.setMinimumAmount(dto.getMinimumAmount());
        product.setSeason(dto.getSeason());
        product.setEnabled(dto.getEnabled());
        product.setCost(dto.getCost());
        product.setRetailPrice(dto.getRetailPrice());
        return product;
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        return productRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        return convertToDto(productRepository.save(product));
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        productDTO.setProductId(id);
        return save(productDTO);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void incrementAmount(Long id) {
        productRepository.incrementAmount(id);
    }

    @Override
    public void decrementAmount(Long id) {
        productRepository.decrementAmount(id);
    }

}