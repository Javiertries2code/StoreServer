package com.elorrieta.storeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long userId;
    private String email;
    private byte enabled;
    private byte[] image;
    private String name;
    private String pass;
    private String rol;
}
