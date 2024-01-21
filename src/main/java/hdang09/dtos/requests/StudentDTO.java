package hdang09.dtos.requests;

import hdang09.enums.Gender;
import hdang09.constants.Regex;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {
    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Roll number is required")
    @Pattern(regexp = Regex.ROLL_NUMBER, message = "Invalid roll number format")
    @Schema(example = "SE171362")
    private String rollNumber;

    @NotNull(message = "Gender ('MALE', 'FEMALE') is required")
    private Gender gender;

    @PastOrPresent(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "ID card is required")
    @Pattern(regexp = Regex.IDENTITY_CARD, message = "Invalid identity card format")
    private String idCard;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    @Schema(example = "contact@hdang09.tech")
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = Regex.PHONE_NUMBER_REGEX, message = "Invalid phone number format")
    private String phone;

    private String address;
}
