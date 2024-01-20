package hdang09.dtos.requests;

import hdang09.constants.Regex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MarkDeleteDTO {
    @NotBlank(message = "Roll number is required")
    @Pattern(regexp = Regex.ROLL_NUMBER, message = "Invalid roll number format")
    private String rollNumber;

    @NotBlank(message = "Subject code is required")
    private String subjectCode;
}
