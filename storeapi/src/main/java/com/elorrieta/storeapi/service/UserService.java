package com.elorrieta.storeapi.service;

import com.elorrieta.storeapi.dto.UserDto;
import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(Long id);
    UserDto save(UserDto userDto);
    UserDto update(Long id, UserDto userDto);
    void delete(Long id);
}
