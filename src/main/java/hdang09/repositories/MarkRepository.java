package hdang09.repositories;

import hdang09.entities.Mark;
import hdang09.entities.MarkId;
import hdang09.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, MarkId> {
    List<Mark> findBySubject(Subject subject);

}
