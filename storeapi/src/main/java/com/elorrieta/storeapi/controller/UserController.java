
package com.elorrieta.storeapi.controller;

import com.elorrieta.storeapi.dto.ProductDTO;
import com.elorrieta.storeapi.dto.UserDto;
import com.elorrieta.storeapi.response.ApiResponse;
import com.elorrieta.storeapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping
//    public List<UserDto> getAllUsers() {
//        return userService.findAll();
//    }
    
    @GetMapping()
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllProducts() {
        List<UserDto> items = userService.findAll();

        ApiResponse<List<UserDto>> response = ApiResponse.<List<UserDto>>builder()
            .success(true)
            .status("success")
            .message("Users list retrieved successfully")
            .type("listuser")
            .data(items)
            .build();

        return ResponseEntity.ok(response);
    }
    

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
