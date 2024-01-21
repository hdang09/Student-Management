package hdang09.dtos.responses;

import hdang09.enums.EntityStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class SubjectResponseDTO {
    private UUID subjectId;
    private String subjectCode;
    private String subjectName;
    private String description;
    private int creditNumber;
    private EntityStatus status;
}
