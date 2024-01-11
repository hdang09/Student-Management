package hdang09.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mark")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Mark {

    @Id
    @Column(name = "mark_id")
    private String markId;

    @Column(name = "subject_id")
    private String subjectId;

    @Column(name = "mark")
    private int mark;

    @Column(name = "input_date")
    private LocalDateTime inputDate;

    @Column(name = "note")
    private String note;
}

