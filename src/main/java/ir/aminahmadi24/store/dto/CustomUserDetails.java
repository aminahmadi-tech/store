package ir.aminahmadi24.store.dto;

import ir.aminahmadi24.store.entity.UserEntity;
import ir.aminahmadi24.store.enumeration.UserRole;
import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    @Getter
    private final Long id;

    private final String email;
    private final String password;

    @Getter
    private final UserRole role;

    public CustomUserDetails(UserEntity userEntity){
        id = userEntity.getId();
        email = userEntity.getEmail();
        password  = userEntity.getPassword();
        role = userEntity.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+ role.name()));
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

}
