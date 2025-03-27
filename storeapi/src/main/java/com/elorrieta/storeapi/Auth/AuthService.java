package com.elorrieta.storeapi.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elorrieta.storeapi.model.User;
import com.elorrieta.storeapi.repository.UserRepository;
import com.elorrieta.storeapi.security.LoginRequest;
import com.elorrieta.storeapi.security.RegisterRequest;
import com.elorrieta.storeapi.security.TokenResponse;
import com.elorrieta.storeapi.service.UserService;
import com.elorrieta.storeapi.security.TokenManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private TokenManager tokenManager;

	
	  public TokenResponse register(RegisterRequest request) {
		  
		  User user = new User();  // Crear instancia de User
		    
		    user.setName(request.name());
		    user.setEmail(request.email());
		    user.setPass(passwordEncoder.encode(request.password())); 
		    user.setEnabled((byte) 1); 
		    user.setRol("USER");  
//	        var user = User.builder()
//	        		.name(request.name())
//	                .email(request.email())
//	                .pass(passwordEncoder.encode(request.password()))
//	                .enabled((byte) 1) 
//	                .rol("USER") 
//	                .build();
	        
	        var savedUser = userRepository.save( user);
	        var jwtToken = jwtService.generateToken(savedUser);
	        var refreshToken = jwtService.generateRefreshToken(savedUser);
	        //saveUserToken(savedUser, jwtToken);
	
	    	tokenManager.addToken(jwtToken);
			tokenManager.addRefreshToken(refreshToken);
			return (new TokenResponse(jwtToken, refreshToken));

	    }
	  
	public TokenResponse login(LoginRequest request) {
		
    	System.out.println("ENTERIN login");

		System.out.println(request.email()+"  "+ request.password());
		var validUSer = valid_login(request);
	
		if (validUSer == null)
			System.out.println("no se ha encontrado el usuario");
		else
			System.out.println(validUSer.toString());
		
		var jwtToken = jwtService.generateToken(validUSer);
		var refreshToken = jwtService.generateRefreshToken(validUSer);

		tokenManager.addToken(jwtToken);
		tokenManager.addRefreshToken(refreshToken);
		return (new TokenResponse(jwtToken, refreshToken));

	}

	public User valid_login(LoginRequest loginRequest) {
		return userRepository.findByEmail(loginRequest.email())
				.filter(user -> user.getPass().equals(loginRequest.password())).orElse(null);
	}

}
