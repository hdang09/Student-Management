package hdang09.dtos.responses;

import hdang09.enums.EntityStatus;
import hdang09.enums.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class StudentResponseDTO {
    private UUID studentId;
    private String fullName;
    private String rollNumber;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String idCard;
    private String email;
    private String phone;
    private String address;
    private EntityStatus status;
}
