package hdang09.entities;

import hdang09.enums.EntityStatus;
import lombok.*;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "subject")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "subject_id")
    private UUID subjectId;

    @Column(name = "subject_code")
    private String subjectCode;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "description")
    private String description;

    @Column(name = "credit_number")
    private int creditNumber;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.ACTIVE;
}

