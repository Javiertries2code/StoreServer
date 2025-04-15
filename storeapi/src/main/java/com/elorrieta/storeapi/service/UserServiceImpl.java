package com.elorrieta.storeapi.service;

import com.elorrieta.storeapi.dto.UserDto;
import com.elorrieta.storeapi.exception.ApiException;
import com.elorrieta.storeapi.exception.ErrorCode;
import com.elorrieta.storeapi.model.User;
import com.elorrieta.storeapi.repository.UserRepository;
import com.elorrieta.storeapi.security.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setEmail(user.getEmail());
        dto.setEnabled(user.getEnabled());
        dto.setImage(user.getImage());
        dto.setName(user.getName());
        dto.setPass(user.getPass());
        dto.setRol(user.getRol());
        return dto;
    }

    private User convertToEntity(UserDto dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setEmail(dto.getEmail());
        user.setEnabled(dto.getEnabled());
        user.setImage(dto.getImage());
        user.setName(dto.getName());
        user.setPass(dto.getPass());
        user.setRol(dto.getRol());
        return user;
    }

    @Override
    public List<UserDto> findAll() {
        try {
            return userRepository.findAll().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ApiException(ErrorCode.DB_ERROR);
        }
    }

    @Override
    public UserDto findById(Long id) {
        try {
            return userRepository.findById(id)
                    .map(this::convertToDto)
                    .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(ErrorCode.DB_ERROR);
        }
    }

    @Override
    public UserDto save(UserDto userDto) {
        try {
            User user = convertToEntity(userDto);
            return convertToDto(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(ErrorCode.DUPLICATE_USER);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.DB_ERROR);
        }
    }

    // Sobrecarga útil en ciertos casos como registro desde lógica JWT
    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(ErrorCode.DUPLICATE_USER);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.DB_ERROR);
        }
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        if (!userRepository.existsById(id)) {
            throw new ApiException(ErrorCode.USER_NOT_FOUND);
        }
        userDto.setUserId(id);
        try {
            return save(userDto);
        } catch (ApiException e) {
          
            throw new ApiException(ErrorCode.USER_NOT_UPDATED); 
        }
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ApiException(ErrorCode.USER_NOT_FOUND);
        }
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.PRODUCT_NOT_DELETED); // Igual que antes, puedes añadir uno tipo USER_NOT_DELETED
        }
    }
}
