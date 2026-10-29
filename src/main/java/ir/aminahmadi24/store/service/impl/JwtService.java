package ir.aminahmadi24.store.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import ir.aminahmadi24.store.dto.UserDto;
import ir.aminahmadi24.store.enumeration.TokenType;
import ir.aminahmadi24.store.enumeration.UserRole;
import ir.aminahmadi24.store.service.TokenService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService implements TokenService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access-token-expiration-second}")
    private Long accessTokenExpirationSecond;

    @Value("${jwt.refresh-token-expiration-second}")
    private Long refreshTokenExpirationSecond;

    private SecretKey secretKey;

    /*public JwtService(@Value("${jwt.secret}") String jwtSecret,
                      @Value("${jwt.access-token-expiration-second}") Long accessTokenExpirationSecond,
                      @Value("${jwt.refresh-token-expiration-second}") Long refreshTokenExpirationSecond){
        this.jwtSecret = jwtSecret;
        this.accessTokenExpirationSecond = accessTokenExpirationSecond;
        this.refreshTokenExpirationSecond = refreshTokenExpirationSecond;
        byte[] bytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        secretKey = Keys.hmacShaKeyFor(bytes);
    }*/

    @PostConstruct
    void init(){
        byte[] bytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        secretKey = Keys.hmacShaKeyFor(bytes);
    }

    @Override
    public String generateAccessToken(Long id, UserRole role) {
        return generateToken(id, role, accessTokenExpirationSecond, TokenType.ACCESS_TOKEN);
    }

    @Override
    public String generateRefreshToken(Long id, UserRole role) {
        return generateToken(id, role, refreshTokenExpirationSecond, TokenType.REFRESH_TOKEN);
    }

    private String generateToken(Long id, UserRole role, Long expirationSecond, TokenType tokenType){
        return Jwts.builder()
                .subject(id.toString())
                .claim("role", role.name())
                .claim("type", tokenType.name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (expirationSecond * 1000)))
                .signWith(secretKey)
                .compact();
    }

    @Override
    public Claims extractClaims(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
