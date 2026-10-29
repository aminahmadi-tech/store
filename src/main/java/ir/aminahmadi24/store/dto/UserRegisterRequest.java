package ir.aminahmadi24.store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public class UserRegisterRequest {
    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters.")
    private final String name;

    @NotBlank(message = "Email is required.")
    @Size(max = 255, message = "Maximum length of email is 255 characters.")
    @Email(message = "Email format is invalid.")
    private final String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 24, message = "Password should be between 6 and 24 characters.")
    private final String password;
}
