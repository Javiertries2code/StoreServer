
package com.elorrieta.storeapi.service;

import com.elorrieta.storeapi.dto.UserDto;
import com.elorrieta.storeapi.model.User;
import com.elorrieta.storeapi.repository.UserRepository;
import com.elorrieta.storeapi.security.LoginRequest;
import com.elorrieta.storeapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    
   
    @Override
    public UserDto save(UserDto userDto) {
        User user = convertToEntity(userDto);
        return convertToDto(userRepository.save(user));
    }
    
    //sobrecarga del metodoguardar, para poder usarlo mientras probando el registro jwt
//    @Override
//    public User save (User user) {
//       
//        return (userRepository.save(user));
//    }
    
    //in further implementacion, will have to consider the encription, already in 

    
    //    @Override
//    public User valid_login(LoginRequest loginRequest) {
//        return userRepository.findByEmail(loginRequest.email())
//                .filter(user -> user.getPass().equals(loginRequest.password()))
//                .orElse(null);
//    }


    
    @Override
    public UserDto update(Long id, UserDto userDto) {
        userDto.setUserId(id);
        return save(userDto);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
