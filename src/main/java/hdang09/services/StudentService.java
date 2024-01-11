package hdang09.services;

import hdang09.constants.ResponseStatus;
import hdang09.dtos.StudentCreateDTO;
import hdang09.entities.Response;
import hdang09.entities.Student;
import hdang09.mappers.StudentMapper;
import hdang09.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<Response<List<Student>>> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        Response<List<Student>> response = new Response<>(ResponseStatus.SUCCESS, "Get all students successfully", students);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Response<Student>> createStudent(StudentCreateDTO studentDTO) {
        logger.info("StudentDTO: {}", studentDTO.toString());
        Student student = StudentMapper.INSTANCE.toEntity(studentDTO);
        logger.info("Student: {}", student);
        Student createdStudent = studentRepository.save(student);

        Response<Student> response = new Response<>(ResponseStatus.SUCCESS, "Created", createdStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<Response> updateStudent(Student student, String studentId) {

        return null;
    }

    public ResponseEntity<Response> deleteStudent(String studentId) {
        return null;
    }

    public ResponseEntity<Response<List<Student>>> getStudentsByKeyword(String keyword) {
        return null;
    }
}
