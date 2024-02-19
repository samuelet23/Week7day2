package Week7.Day1.model.Auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull(message = "Il valore username  non può essere null")
    @NotEmpty(message = "Il valore  username non può essere vuoto")
    private String username;

    @NotNull(message = "Il valore username  non può essere null")
    @NotEmpty(message = "Il valore  username non può essere vuoto")
    private String password;

}
