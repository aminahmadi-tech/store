package ir.aminahmadi24.store.service.impl;

import ir.aminahmadi24.store.dto.*;
import ir.aminahmadi24.store.entity.UserEntity;
import ir.aminahmadi24.store.enumeration.CustomError;
import ir.aminahmadi24.store.enumeration.UserRole;
import ir.aminahmadi24.store.exception.EmailAlreadyExistsException;
import ir.aminahmadi24.store.mapper.UserMapper;
import ir.aminahmadi24.store.repository.UserRepository;
import ir.aminahmadi24.store.service.AuthService;
import ir.aminahmadi24.store.service.TokenService;
import ir.aminahmadi24.store.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenService jwtService;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        String email = Util.normalizeEmail(request.getEmail());
        boolean exists = userRepository.existsByEmail(email);
        if (exists)
            throw new EmailAlreadyExistsException(CustomError.EMAIL_ALREADY_EXISTS, "Email already exists.");

        UserEntity userEntity = new UserEntity();
        userEntity.setName(request.getName());
        userEntity.setEmail(email);
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setRole(UserRole.USER);
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.userEntityToUserRegisterResponse(savedUser);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(
                customUserDetails.getId(), customUserDetails.getRole());
        return new UserLoginResponse(accessToken);

    }

}
