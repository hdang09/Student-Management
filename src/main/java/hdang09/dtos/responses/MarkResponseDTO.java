package hdang09.dtos.responses;

import hdang09.entities.Student;
import hdang09.entities.Subject;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MarkResponseDTO {
    private Student student;
    private Subject subject;
    private float mark;
    private LocalDateTime inputDate;
    private LocalDateTime updateDate;
    private String note;
}
