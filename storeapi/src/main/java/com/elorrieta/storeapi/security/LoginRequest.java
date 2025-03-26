package com.elorrieta.storeapi.security;

public record LoginRequest(
	String email,
	String password)
{}