package com.elorrieta.storeapi.auth;

import com.elorrieta.storeapi.controller.ProductController;
import com.elorrieta.storeapi.dto.ProductDTO;
import com.elorrieta.storeapi.exception.ApiException;
import com.elorrieta.storeapi.exception.ErrorCode;
import com.elorrieta.storeapi.helpers.CryptoHelper;
import com.elorrieta.storeapi.response.ApiResponse;
import com.elorrieta.storeapi.security.LoginRequest;
import com.elorrieta.storeapi.security.RegisterRequest;
import com.elorrieta.storeapi.security.TokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final CryptoHelper cryptoHelper;

	
	@Autowired
    private  AuthService service ; 
	
	@GetMapping
    public ResponseEntity<TokenResponse> testingCall() {
        final TokenResponse token = new TokenResponse("test token", "Another test", "test3", "test4");
        return ResponseEntity.ok(token);
    }
	
//    @PostMapping("/login")
//    public ResponseEntity<TokenResponse> authenticate(@RequestBody final LoginRequest request) {
//    	System.out.println("ENTERIN AUTHENTICATION LOGIN");
//        final TokenResponse token = service.login(request);
//        return ResponseEntity.ok(token);
//    }

	@PostMapping(value = "", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> createProduct(@RequestBody String encryptedBody) {
	    try {
	        String decryptedJson = cryptoHelper.decrypt(encryptedBody);
	       
	       
	        LoginRequest request = new ObjectMapper().readValue(decryptedJson, LoginRequest.class);
	      

	        final TokenResponse token = service.login(request);
	        ApiResponse<TokenResponse>response = ApiResponse.<TokenResponse>builder()
	                .success(true)
	                .status("success")
	                .message("Corret User Login")
	                .type("token")
	                .data(token)
	                .build();

	            return ResponseEntity.ok(response);
	       
	    } catch (Exception e) {
	        log.error("Error al desencriptar createProduct", e);
	        throw new ApiException(ErrorCode.DECRYPTION_ERROR);
	    }
	}
	
    /////////
    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody final RegisterRequest request) {
        final TokenResponse token = service.register(request);
        return ResponseEntity.ok(token);
    }

//    @PostMapping("/login")
//    public ResponseEntity<TokenResponse> authenticate(@RequestBody final LoginRequest request) {
//    	System.out.println("ENTERIN AUTHENTICATION LOGIN");
//        final TokenResponse token = service.login(request);
//        return ResponseEntity.ok(token);
//    }

//    @PostMapping("/refresh")
//    public TokenResponse refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
//        return service.refreshToken(authHeader);
//    }
} 
