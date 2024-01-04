package ecommerce.security.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
