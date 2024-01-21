package hdang09.repositories;

import hdang09.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, UUID> {

    Subject findBySubjectCode(String subjectCode);

    @Query("SELECT s FROM Subject s WHERE s.status = 'ACTIVE'")
    List<Subject> getActiveSubjects();

    @Query("SELECT s FROM Subject s " +
            "WHERE s.subjectCode LIKE %?1% OR s.subjectName LIKE %?1% OR s.description LIKE %?1% ")
    List<Subject> search(String keyword);

    @Modifying
    @Transactional
    @Query("UPDATE Subject s SET s.status = 'INACTIVE' WHERE s.subjectId = ?1")
    int deleteSubject(UUID subjectId);
}
