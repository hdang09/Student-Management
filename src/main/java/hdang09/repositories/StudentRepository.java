package hdang09.repositories;

import hdang09.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    Student findByRollNumber(String rollNumber);

    @Query("SELECT s FROM Student s WHERE s.status = 'ACTIVE'")
    List<Student> getActiveStudents();

    @Query("SELECT s FROM Student s " +
            "WHERE s.fullName LIKE %?1% OR s.rollNumber LIKE %?1% OR s.idCard LIKE %?1% OR s.email LIKE %?1% " +
            "OR s.phone LIKE %?1% OR s.address LIKE %?1% AND s.status = 'ACTIVE'")
    List<Student> search(String keyword);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.status = 'INACTIVE' WHERE s.studentId = ?1 AND s.status = 'ACTIVE'")
    int deleteStudent(UUID studentId);
}
