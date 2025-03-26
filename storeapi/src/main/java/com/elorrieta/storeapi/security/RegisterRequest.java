package com.elorrieta.storeapi.security;

public record RegisterRequest(
	    String email,
	    String password,
	    String name
	) {}
