package com.elorrieta.storeapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String status;
    private String message;
    private T data;
    private String type;

    @Builder.Default
    private String accessToken = null;

    @Builder.Default
    private String refreshToken = null;
}
