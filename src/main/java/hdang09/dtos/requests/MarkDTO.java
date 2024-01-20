package hdang09.dtos.requests;

import hdang09.constants.Regex;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MarkDTO {
    @NotBlank(message = "Roll number is required")
    @Pattern(regexp = Regex.ROLL_NUMBER, message = "Invalid roll number format")
    private String rollNumber;

    @NotBlank(message = "Subject code is required")
    private String subjectCode;

    @NotBlank(message = "Mark is required")
    @Min(value = 0, message = "Mark must be greater than or equal to 0")
    @Max(value = 10, message = "Mark must be less than or equal to 10")
    private float mark;

    private String note;
}
