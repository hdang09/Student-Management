package hdang09.entities;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mark")
@IdClass(MarkId.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mark {

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "mark")
    private float mark;

    @Column(name = "input_date")
    private LocalDateTime inputDate = LocalDateTime.now();

    @Column(name = "update_date")
    private LocalDateTime updateDate = LocalDateTime.now();

    @Column(name = "note")
    private String note;
}

