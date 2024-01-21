package hdang09.repositories;

import hdang09.entities.Mark;
import hdang09.entities.MarkId;
import hdang09.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, MarkId> {
    List<Mark> findBySubject(Subject subject);

    @Query("DELETE FROM Mark m WHERE m.student.rollNumber = ?1 AND m.subject.subjectCode = ?2")
    void deleteMark(String rollNumber, String subjectCode);

    @Modifying
    @Transactional
    @Query("UPDATE Mark m SET m.mark = ?3, m.note = ?4 WHERE m.student.rollNumber = ?1 AND m.subject.subjectCode = ?2")
    int updateMark(String rollNumber, String subjectCode, float mark, String note);

    @Query("SELECT m FROM Mark m WHERE m.student.rollNumber = ?1")
    List<Mark> findByRollNumber(String rollNumber);

}
