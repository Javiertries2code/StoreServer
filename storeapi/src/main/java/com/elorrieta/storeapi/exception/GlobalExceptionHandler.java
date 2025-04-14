package com.elorrieta.storeapi.exception;

import com.elorrieta.storeapi.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de errores personalizados de negocio
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Object>> handleApiException(ApiException ex) {
        ErrorCode error = ex.getErrorCode();

        ApiResponse<Object> response = ApiResponse.builder()
            .success(false)
            .status(error.getCode())
            .message(error.getMessage())
            .type("business")
            .data(null)
            .build();

        return ResponseEntity.badRequest().body(response);
    }

    // Manejo de errores típicos por entidades no encontradas (opcional)
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgs(Exception ex) {
        ApiResponse<Object> response = ApiResponse.builder()
            .success(false)
            .status(ErrorCode.VALIDATION_ERROR.getCode())
            .message(ex.getMessage())
            .type("validation")
            .data(null)
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Manejo genérico de cualquier error inesperado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {
        ex.printStackTrace(); // opcional: log completo
        ApiResponse<Object> response = ApiResponse.builder()
            .success(false)
            .status(ErrorCode.UNKNOWN_ERROR.getCode())
            .message("Unexpected server error")
            .type("exception")
            .data(null)
            .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
