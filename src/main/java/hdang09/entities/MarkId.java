package hdang09.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MarkId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarkId)) return false;
        MarkId that = (MarkId) o;
        return getStudent().equals(that.getStudent()) && getSubject().equals(that.getSubject());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}

