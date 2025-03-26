package com.elorrieta.storeapi.security;


public class Token {
    public Long id;
    public String token;
    public TokenType tokenType;
    public boolean revoked;
    public boolean expired;
    public String user_email;

    public Token() {}

    public Token(Long id, String token, TokenType tokenType, boolean revoked, boolean expired, String user_email) {
        this.id = id;
        this.token = token;
        this.tokenType = tokenType;
        this.revoked = revoked;
        this.expired = expired;
        this.user_email = user_email;
    }
}
