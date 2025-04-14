package com.elorrieta.storeapi.controller;

import com.elorrieta.storeapi.dto.ProductDTO;
import com.elorrieta.storeapi.helpers.CryptoHelper;
import com.elorrieta.storeapi.helpers.PrintDebug;
import com.elorrieta.storeapi.service.ProductService;
import com.elorrieta.storeapi.service.StockStatusChecker;
import com.elorrieta.storeapi.service.email.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final StockStatusChecker stockStatusChecker;
	private final CryptoHelper cryptoHelper;

	@Autowired
	private ProductService productService;

	@Autowired
	private  PrintDebug pd;

	ProductController(StockStatusChecker stockStatusChecker, CryptoHelper cryptoHelper) {
		this.stockStatusChecker = stockStatusChecker;
		this.cryptoHelper = cryptoHelper;
	}

	@GetMapping
	public List<ProductDTO> getAllProducts() {
		return productService.findAll();
	}

	@GetMapping("/{id}")
	public ProductDTO getProductById(@PathVariable Long id) {

		return productService.findById(id);
	}
	
	@PostMapping(value = "", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> createEncrypted(@RequestBody String encryptedBody) {
	    try {
	        String decryptedJson = cryptoHelper.decrypt(encryptedBody);
	       
	       
	       ProductDTO product = new ObjectMapper().readValue(decryptedJson, ProductDTO.class);
	       pd.pM(decryptedJson,  product.getName());
	       return ResponseEntity.ok(productService.save(product));
	    } catch (Exception e) {
	        log.error("Error al desencriptar createProduct", e);
	        return ResponseEntity.badRequest().body("Error al procesar el cuerpo");
	    }
	}
	
/*
	@PostMapping
	public ProductDTO createProduct(@RequestBody ProductDTO ProductDTO) {
		return productService.save(ProductDTO);
	}*/

	@PutMapping(value = "/{id}", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> updateEncrypted(@PathVariable Long id, @RequestBody String encryptedBody) {

		try {
			String decryptedJson = cryptoHelper.decrypt(encryptedBody);
			log.debug(" JSON desencriptado: {}", decryptedJson);

			ObjectMapper objectMapper = new ObjectMapper();
			ProductDTO product = objectMapper.readValue(decryptedJson, ProductDTO.class);
			
			ProductDTO updated = productService.update(id, product);
			
			updated.setAmount(55555);
			
			return ResponseEntity.ok(updated);

		} catch (Exception e) {
			log.error("Error procesando mensaje encriptado", e);
			return ResponseEntity.badRequest().body("Error al desencriptar o procesar el cuerpo");
		}
	}
/*
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
		pd.pM("updateProduct -- product received in controller\n", productDTO.getAmount());

    	ProductDTO updated = productService.update(id, productDTO);
		pd.pM("updateProduct -- updated product received in controller\n", updated.getAmount());
		updated.setAmount(55555);

    	return updated;
    }*/

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.delete(id);
	}

	// ADD substract
	@PostMapping("/plus/{id}")
	public void addAmount(@PathVariable Long id) {
		productService.incrementAmount(id);
	}

	@PostMapping("/minus/{id}")
	public void subtractAmount(@PathVariable Long id) {
		stockStatusChecker.checkAndDecrement(id);
	}

}
