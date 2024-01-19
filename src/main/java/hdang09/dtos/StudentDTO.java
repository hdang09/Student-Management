package hdang09.dtos;

import hdang09.constants.Gender;
import hdang09.constants.Regex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {
    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Roll number is required")
    @Pattern(regexp = Regex.ROLL_NUMBER, message = "Invalid roll number format")
    private String rollNumber;

    @NotNull(message = "Gender ('MALE', 'FEMALE') is required")
    private Gender gender;

    @NotBlank(message = "Date of birth is required")
    private LocalDateTime dateOfBirth;

    @NotBlank(message = "ID card is required")
    @Pattern(regexp = Regex.IDENTITY_CARD, message = "Invalid identity card format")
    private String idCard;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = Regex.PHONE_NUMBER_REGEX, message = "Invalid phone number format")
    private String phone;

    private String address;
}
