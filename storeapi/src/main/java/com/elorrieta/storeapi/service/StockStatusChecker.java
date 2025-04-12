package com.elorrieta.storeapi.service;


import com.elorrieta.storeapi.dto.ProductDTO;
import com.elorrieta.storeapi.service.ProductService;
import com.elorrieta.storeapi.service.email.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value; // ✅ Esta es la buena

@Slf4j
@Component
@RequiredArgsConstructor
public class StockStatusChecker {

    private final ProductService productService;
    private final EmailService emailService;
   
    @Value("${email.receiver}")
    private String receiver;
    

    public void checkAndDecrement(Long productId) {
    	
        log.info("📦 Entrando en checkAndDecrement con ID: {}", productId);

        // Step 1: Decrement
        productService.decrementAmount(productId);

        // Step 2: Get updated product
        ProductDTO product = productService.findById(productId);

        // Step 3: Compare amount and minimumAmount
        if (product.getAmount() == product.getMinimumAmount()) {
            try {
                emailService.sendMail(
                		receiver.toString(),  
                        "The product '" + product.getName() + "' ha alcanzado su Stock minimo de " + product.getAmount() + " unidades"
                );
            } catch (MessagingException e) {
                // Aquí podrías loguear o lanzar una excepción propia
                System.err.println("Failed to send email: " + e.getMessage());
            }
        }
    }
}