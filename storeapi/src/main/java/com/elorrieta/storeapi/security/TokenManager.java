package com.elorrieta.storeapi.security;


import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TokenManager {

    private final List<String> tokens = new ArrayList<>();
    
    public void addToken(String token) {
        tokens.add(token);
    }

    public List<String> getAllTokens() {
        return tokens;
    }

    public Optional<String> findByToken(String tokenValue) {
        return tokens.stream()
                .filter(t -> t.equals(tokenValue))
                .findFirst();
    }

    public void clearAllTokens() {
        tokens.clear();
    }
    
  //refreshtokens
    private final List<String> refresh = new ArrayList<>();
    
    public void addRefreshToken(String token) {
    	refresh.add(token);
    }

    public List<String> getAllRefreshTokens() {
        return refresh;
    }

    public Optional<String> findByRefreshToken(String tokenValue) {
        return refresh.stream()
                .filter(t -> t.equals(tokenValue))
                .findFirst();
    }

    public void clearAllRefreshTokens() {
    	refresh.clear();
    }
    
    
}
