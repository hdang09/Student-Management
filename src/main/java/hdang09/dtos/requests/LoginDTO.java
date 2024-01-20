package hdang09.dtos.requests;

import hdang09.constants.Regex;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "Username is required")
    @Schema(example = "hdang09")
    private String username;

    @Pattern(
            regexp = Regex.PASSWORD_REGEX,
            message = "Password must be at least 8 characters long, contain at least 1 uppercase letter, 1 lowercase letter, 1 number and 1 special character"
    )
    @Schema(example = "Password123")
    private String password;
}