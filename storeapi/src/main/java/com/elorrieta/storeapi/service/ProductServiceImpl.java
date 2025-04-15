package com.elorrieta.storeapi.service;

import com.elorrieta.storeapi.dto.ProductDTO;
import com.elorrieta.storeapi.exception.ApiException;
import com.elorrieta.storeapi.exception.ErrorCode;
import com.elorrieta.storeapi.model.Product;
import com.elorrieta.storeapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            return productRepository.findAll().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
        	 e.printStackTrace();
            throw new ApiException(ErrorCode.DB_ERROR);
        }
    }

    @Override
    public ProductDTO findById(Long id) {
        try {
            return productRepository.findById(id)
                    .map(this::convertToDto)
                    .orElseThrow(() -> new ApiException(ErrorCode.PRODUCT_NOT_FOUND));
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(ErrorCode.DB_ERROR);
        }
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        try {
            Product product = convertToEntity(productDTO);
            return convertToDto(productRepository.save(product));
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(ErrorCode.DUPLICATE_PROD);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.DB_ERROR);
        }
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        if (!productRepository.existsById(id)) {
            throw new ApiException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        productDTO.setProductId(id);
        try {
            return save(productDTO);
        } catch (ApiException e) {
          
            throw new ApiException(ErrorCode.PRODUCT_NOT_UPDATED);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.DB_ERROR);
        }
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ApiException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.PRODUCT_NOT_DELETED);
        }
    }

    @Override
    public void incrementAmount(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ApiException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        try {
            productRepository.incrementAmount(id);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.PRODUCT_NOT_UPDATED);
        }
    }

    @Override
    public void decrementAmount(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ApiException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        try {
            productRepository.decrementAmount(id);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.PRODUCT_NOT_UPDATED);
        }
    }
}
