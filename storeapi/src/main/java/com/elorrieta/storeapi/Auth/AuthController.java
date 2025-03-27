package com.elorrieta.storeapi.Auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elorrieta.storeapi.security.LoginRequest;
import com.elorrieta.storeapi.security.RegisterRequest;
import com.elorrieta.storeapi.security.TokenResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	@Autowired
    private  AuthService service ; 
	
	@GetMapping
    public ResponseEntity<TokenResponse> testingCall() {
    	System.out.println("ENTERIN AUTHENTICATION TEST");
        final TokenResponse token = new TokenResponse("test token", "Another test");
        return ResponseEntity.ok(token);
    }
	
//    @Autowired
//   private final AuthService service:
    //the point of all this, is to use anotacions instead of new instances, but it throusghs error
    
    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody final RegisterRequest request) {
        final TokenResponse token = service.register(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody final LoginRequest request) {
    	System.out.println("ENTERIN AUTHENTICATION LOGIN");
        final TokenResponse token = service.login(request);
        return ResponseEntity.ok(token);
    }

//    @PostMapping("/refresh")
//    public TokenResponse refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
//        return service.refreshToken(authHeader);
//    }
} 
