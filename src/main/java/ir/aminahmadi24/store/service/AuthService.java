package ir.aminahmadi24.store.service;

import ir.aminahmadi24.store.dto.UserLoginRequest;
import ir.aminahmadi24.store.dto.UserLoginResponse;
import ir.aminahmadi24.store.dto.UserRegisterRequest;
import ir.aminahmadi24.store.dto.UserRegisterResponse;

public interface AuthService {
    UserRegisterResponse register(UserRegisterRequest request);
    UserLoginResponse login(UserLoginRequest request);
}
