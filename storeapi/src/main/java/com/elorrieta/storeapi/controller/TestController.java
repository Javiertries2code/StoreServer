package com.elorrieta.storeapi.controller;

import com.elorrieta.storeapi.dto.UserDto;
import com.elorrieta.storeapi.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserDto>>> getDummyUsers() {
        UserDto user1 = new UserDto(1L, "alice@example.com", (byte) 1, null, "Alice", "pass123", "admin");
        UserDto user2 = new UserDto(2L, "bob@example.com", (byte) 1, null, "Bob", "pass456", "user");

        List<UserDto> dummyUsers = List.of(user1, user2);

        ApiResponse<List<UserDto>> response = ApiResponse.<List<UserDto>>builder()
            .success(true)
            .status("success")
            .message("Dummy user list for test")
            .type("listuser")
            .data(dummyUsers)
            .build();

        return ResponseEntity.ok(response);
    }
}
