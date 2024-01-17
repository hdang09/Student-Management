package hdang09.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubjectDTO {
    private String subjectCode; // TODO: Add validation bean
    private String subjectName;
    private String description;
    private int creditNumber;
}
