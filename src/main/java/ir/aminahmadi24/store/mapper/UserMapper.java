package ir.aminahmadi24.store.mapper;

import ir.aminahmadi24.store.dto.UserRegisterResponse;
import ir.aminahmadi24.store.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserRegisterResponse userEntityToUserRegisterResponse(UserEntity userEntity){
        return new UserRegisterResponse(
                userEntity.getId(), userEntity.getName(), userEntity.getEmail(),
                userEntity.getRole(), userEntity.getCreatedAt(), userEntity.getUpdatedAt()
        );
    }
}
