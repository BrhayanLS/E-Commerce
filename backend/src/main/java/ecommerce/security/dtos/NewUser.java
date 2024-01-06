package ecommerce.security.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class NewUser {

    @NotBlank
    private String userName;
    @Email
    private String email;
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();
    public NewUser() {
    }
    public NewUser(@NotBlank String userName, @Email String email, @NotBlank String password,Set<String> roles) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
