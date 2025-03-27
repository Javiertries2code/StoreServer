package com.elorrieta.storeapi.security;

public record RegisterRequest(
		String name,
	    String email,
	    String password
	) {}
