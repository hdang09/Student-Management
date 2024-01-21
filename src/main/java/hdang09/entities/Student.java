package hdang09.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

import hdang09.enums.Gender;
import hdang09.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID studentId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "roll_number")
    private String rollNumber;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.ACTIVE;
}

