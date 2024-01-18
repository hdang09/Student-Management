package hdang09.entities;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mark")
@IdClass(MarkId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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
    private int mark;

    @Column(name = "input_date")
    private LocalDateTime inputDate;

    @Column(name = "note")
    private String note;
}

