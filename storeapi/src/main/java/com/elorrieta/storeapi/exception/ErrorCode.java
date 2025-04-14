package com.elorrieta.storeapi.exception;

public enum ErrorCode {

    // 400 - Validación / petición mal formada
    VALIDATION_ERROR("VAL_400", "Validation failed"),
    BAD_REQUEST("BAD_400", "Bad request"),

    // 401 / 403 - Seguridad
    UNAUTHORIZED("SEC_401", "Unauthorized access"),
    FORBIDDEN("SEC_403", "Forbidden resource"),

    // 404 - No encontrado
    USER_NOT_FOUND("USR_404", "User not found"),
    PRODUCT_NOT_FOUND("PROD_404", "Product not found"),
    RESOURCE_NOT_FOUND("GEN_404", "Resource not found"),

    //405 NOT CREATED
    PRODUCT_NOT_CREATED("PROD_405", "Product not created"),
    
    //406 NOT UPDATED
    PRODUCT_NOT_UPDATED("PROD_406", "Product not updated"),
    
    //407 NOT DELETED
    PRODUCT_NOT_DELETED("PROD_407", "Product not deleted"),
    
    // 409 - Conflictos
    DUPLICATE_USER("USR_409", "User already exists"),
    DUPLICATE_ENTRY("GEN_409", "Duplicate entry"),
    DUPLICATE_PROD("GEN_409", "Duplicate product"),
    
    DECRYPTION_ERROR("CRYPTO_410", "Error decrypting request body"),
    ENCRYPTION_ERROR("CRYPTO_410", "Error encrypting response body"),



    // 500 - Internos
    INTERNAL_ERROR("INT_500", "Internal server error"),
    DB_ERROR("DB_500", "Database error"),
    UNKNOWN_ERROR("GEN_500", "Unknown server error");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
}
