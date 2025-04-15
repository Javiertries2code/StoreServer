package com.elorrieta.storeapi.interceptor;

import com.elorrieta.storeapi.exception.ApiException;
import com.elorrieta.storeapi.exception.ErrorCode;
import com.elorrieta.storeapi.helpers.CryptoHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class EncryptionResponseAdvice implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;
    private final CryptoHelper cryptoHelper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true; // Intercepta todas las respuestas
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
    	
    	log.info(" Encriptando respuesta con EncryptionResponseAdvice");


        try {
            if (body == null || selectedContentType != MediaType.APPLICATION_JSON) {
                return body; // No encriptar si no es JSON o el cuerpo está vacío
            }

            String json = objectMapper.writeValueAsString(body); // Serializa a JSON
            String encrypted = cryptoHelper.encrypt(json);       // Encripta
            log.debug(" Response encrypted");

            return encrypted;
        } catch (Exception e) {
            log.error(" Error encrypting response body", e);
            throw new ApiException(ErrorCode.ENCRYPTION_ERROR);
        }
    }
}
