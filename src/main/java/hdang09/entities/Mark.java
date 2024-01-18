package hdang09.entities;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mark")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Mark {

    @EmbeddedId
    private MarkId id;

    @Column(name = "mark")
    private int mark;

    @Column(name = "input_date")
    private LocalDateTime inputDate;

    @Column(name = "note")
    private String note;
}

