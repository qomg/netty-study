package com.yeahka.it.community.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
@Tag(name = "user")
public class UserController {

    @Operation(description = "user register")
    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserVO userVO) {
        return ResponseEntity.ok("ok");
    }

    @Operation(summary = "get user info by id")
    @GetMapping
    public ResponseEntity<UserVO> userInfo(@RequestParam String userId) {
        return ResponseEntity.ok(new UserVO());
    }
}