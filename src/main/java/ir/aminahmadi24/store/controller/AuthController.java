package ir.aminahmadi24.store.controller;

import ir.aminahmadi24.store.dto.UserRegisterRequest;
import ir.aminahmadi24.store.dto.UserRegisterResponse;
import ir.aminahmadi24.store.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/auth")

public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@Valid @RequestBody UserRegisterRequest request){
        UserRegisterResponse response = authService.register(request);
        URI location = URI.create("/api/v1/auth/" + response.id());
        return ResponseEntity.created(location).body(response);
    }
}
