package ir.aminahmadi24.store;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import ir.aminahmadi24.store.dto.ErrorResponse;
import ir.aminahmadi24.store.entity.UserEntity;
import ir.aminahmadi24.store.enumeration.Message;
import ir.aminahmadi24.store.enumeration.TokenType;
import ir.aminahmadi24.store.exception.BusinessException;
import ir.aminahmadi24.store.exception.UserNotFoundException;
import ir.aminahmadi24.store.repository.UserRepository;
import ir.aminahmadi24.store.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            request.setAttribute("auth_error", Message.INVALID_AUTH_HEADER.name());
            filterChain.doFilter(request, response);
            return;
        }
        try{
            String accessToken = authHeader.replace("Bearer ", "");
            Claims claims = jwtService.extractClaims(accessToken);

            if(!TokenType.ACCESS_TOKEN.name().equals(claims.get("type", String.class))){
                request.setAttribute("auth_error", Message.INVALID_TOKEN_TYPE.name());
                filterChain.doFilter(request, response);
                return;
            }

            Long userId = Long.valueOf(claims.getSubject());
            UserEntity userEntity = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(Message.USER_NOT_FOUND.name()));
            String userRole = userEntity.getRole().name();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userId,
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + userRole))
            );
            usernamePasswordAuthenticationToken.setDetails(
                    new WebAuthenticationDetailsSource()
                            .buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }
        catch (ExpiredJwtException ex){
            request.setAttribute("auth_error", Message.EXPIRED_TOKEN.name());
        }
        catch (JwtException | IllegalArgumentException ex){
            request.setAttribute("auth_error", Message.INVALID_TOKEN.name());
        }
        catch (BusinessException ex) {
            request.setAttribute("auth_error", ex.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
