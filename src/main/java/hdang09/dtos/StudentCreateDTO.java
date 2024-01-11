package hdang09.dtos;

import hdang09.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentCreateDTO {
    private String fullName;
    private String rollNumber;
    private Gender gender;
    private LocalDateTime dateOfBirth;
    private String idCard;
    private String email;
    private String phone;
    private String address;
}
