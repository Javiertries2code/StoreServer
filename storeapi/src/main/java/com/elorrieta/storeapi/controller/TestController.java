package com.elorrieta.storeapi.controller;

import com.elorrieta.storeapi.dto.ProductDTO;
import com.elorrieta.storeapi.dto.UserDto;
import com.elorrieta.storeapi.response.ApiResponse;
import com.elorrieta.storeapi.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@Autowired
	private ProductService productService;
	

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserDto>>> getDummyUsers() {
        UserDto user1 = new UserDto(1L, "alice@example.com", (byte) 1, null, "Alice", "pass123", "admin");
        UserDto user2 = new UserDto(2L, "bob@example.com", (byte) 1, null, "Bob", "pass456", "user");

        List<UserDto> dummyUsers = List.of(user1, user2);

        ApiResponse<List<UserDto>> response = ApiResponse.<List<UserDto>>builder()
            .success(true)
            .status("success")
            .message("Dummy user list for test")
            .type("listuser")
            .data(dummyUsers)
            .build();

        return ResponseEntity.ok(response);
    }
    
      @GetMapping("products")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts() {
        List<ProductDTO> items = productService.findAll();

        ApiResponse<List<ProductDTO>> response = ApiResponse.<List<ProductDTO>>builder()
            .success(true)
            .status("success")
            .message("Products list retrieved successfully")
            .type("listproduct")
            .data(items)
            .build();

        return ResponseEntity.ok(response);
    }
    
    
  
	@GetMapping("products/{id}")
	public ProductDTO getProductById(@PathVariable Long id) {

		return productService.findById(id);
	}
    
}
