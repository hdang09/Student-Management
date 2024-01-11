package hdang09.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

import hdang09.constants.Gender;
import hdang09.constants.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private String studentId = "123"; // TODO: Change to UUID

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "roll_number")
    private String rollNumber;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private StudentStatus status = StudentStatus.ACTIVE;
}

