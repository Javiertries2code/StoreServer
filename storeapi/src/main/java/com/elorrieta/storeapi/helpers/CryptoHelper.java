package com.elorrieta.storeapi.helpers;

import com.google.crypto.tink.subtle.AesGcmJce;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CryptoHelper {

	//@Value("${tink.encryption}")
	private String base64Key = "k0ZH5SszCQEx0pJm9WiG6ER5rhgDuBBJw4sk+IzqZx4="; 
	
	private AesGcmJce aead;

	@PostConstruct
	public void init() {

		try {
			byte[] key = Base64.getDecoder().decode(base64Key);
			this.aead = new AesGcmJce(key);
			log.info("🔐 CryptoHelper initialized");
		} catch (Exception e) {
			log.error("Error initializing CryptoHelper", e);
			throw new RuntimeException(e);
		}
	}

	public String encrypt(String plainText) {
		try {
			byte[] cipher = aead.encrypt(plainText.getBytes(), null);
			return Base64.getEncoder().encodeToString(cipher);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String decrypt(String base64CipherText) {
		try {
			byte[] cipher = Base64.getDecoder().decode(base64CipherText);
			byte[] plain = aead.decrypt(cipher, null);
			return new String(plain);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
