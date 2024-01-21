package hdang09.dtos.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class MarkUpdateDTO {
    @Min(value = 0, message = "Mark must be greater than or equal to 0")
    @Max(value = 10, message = "Mark must be less than or equal to 10")
    private float mark;

    private String note;
}
