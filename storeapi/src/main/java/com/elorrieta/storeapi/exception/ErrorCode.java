package com.elorrieta.storeapi.exception;

public enum ErrorCode {

	//TEST_ERROR
	TESTT_ERROR("0000", "Error testing"),
	
	// 400 - Validación / petición mal formada
	VALIDATION_ERROR("4001", "Validation failed"),
	BAD_REQUEST("4002", "Bad request"),

	// 401 / 403 - Seguridad
	UNAUTHORIZED("4011", "Unauthorized access"), 
	FORBIDDEN("4031", "Forbidden resource"),

	// 404 - No encontrado
	USER_NOT_FOUND("4041", "User not found"), 
	PRODUCT_NOT_FOUND("4042", "Product not found"),
	RESOURCE_NOT_FOUND("4043", "Resource not found"),

	// 405 NOT CREATED
	PRODUCT_NOT_CREATED("4051", "Product not created"),

	// 406 NOT UPDATED
	PRODUCT_NOT_UPDATED("4061", "Product not updated"),
	USER_NOT_UPDATED("4062", "User not updated"),

	// 407 NOT DELETED
	PRODUCT_NOT_DELETED("4071", "Product not deleted"),

	// 409 - Conflictos
	DUPLICATE_USER("4091", "User already exists"), DUPLICATE_ENTRY("4092", "Duplicate entry"),
	DUPLICATE_PROD("4093", "Duplicate product"),

	// 410 - Cifrado
	DECRYPTION_ERROR("4101", "EDECRIPTING ERROR"),
	ENCRYPTION_ERROR("4102", "Error encrypting response body"),
	ENCRYPTION_KEY_ERROR("4103", "Error initializing CryptoHelper"),


	// 500 - Internos
	INTERNAL_ERROR("5001", "Internal server error"), 
	DB_ERROR("5002", "Database error"),
	UNKNOWN_ERROR("5003", "Unknown server error");
	

	private final String code;
	private final String message;

	ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
