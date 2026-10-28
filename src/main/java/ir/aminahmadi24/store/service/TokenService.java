package ir.aminahmadi24.store.service;

import ir.aminahmadi24.store.dto.UserDto;
import ir.aminahmadi24.store.enumeration.UserRole;
import org.springframework.stereotype.Service;


public interface TokenService {
    String generateAccessToken(Long id, UserRole role);
    String generateRefreshToken(Long id, UserRole role);
}
