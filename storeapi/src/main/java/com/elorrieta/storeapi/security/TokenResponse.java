package com.elorrieta.storeapi.security;


import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse(
	
		
	    @JsonProperty("userId")
	    String userId,


	    @JsonProperty("displayName")
	    String displayName,
	
		
    @JsonProperty("access_token")
    String accessToken,

    @JsonProperty("refresh_token")
    String refreshToken
) {
} 
