package hdang09.services;

import hdang09.constants.ResponseStatus;
import hdang09.constants.StudentStatus;
import hdang09.dtos.StudentDTO;
import hdang09.entities.Response;
import hdang09.entities.Student;
import hdang09.mappers.StudentMapper;
import hdang09.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<Response<List<Student>>> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        Response<List<Student>> response = new Response<>(ResponseStatus.SUCCESS, "Get all students successfully", students);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Response<Student>> createStudent(StudentDTO studentDTO) {
        // Save student to database
        Student student = StudentMapper.INSTANCE.toEntity(studentDTO);
        Student createdStudent = studentRepository.save(student);

        Response<Student> response = new Response<>(ResponseStatus.SUCCESS, "Created", createdStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<Response<Student>> updateStudent(StudentDTO studentDTO, String rollNumber) {
        // Check if student exists
        UUID studentId = studentRepository.getStudentIdByRollNumber(rollNumber);
        if (studentId == null) {
            Response<Student> response = new Response<>(ResponseStatus.ERROR, "Cannot find student");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Update student
        Student student = StudentMapper.INSTANCE.toEntity(studentDTO);
        student.setStudentId(studentId);
        Student createdStudent = studentRepository.save(student);

        Response<Student> response = new Response<>(ResponseStatus.SUCCESS, "Updated", createdStudent);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Response> deleteStudent(String rollNumber) {
        Student deleteStudent = studentRepository.findByRollNumber(rollNumber);

        // If student is found, set status to INACTIVE
        if (deleteStudent != null) {
            deleteStudent.setStatus(StudentStatus.INACTIVE);
            studentRepository.save(deleteStudent);

            Response response = new Response(ResponseStatus.SUCCESS, "Deleted");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        // If student is not found, return error
        Response response = new Response(ResponseStatus.ERROR, "Cannot find student");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public ResponseEntity<Response<List<Student>>> getStudentsByKeyword(String keyword) {
        List<Student> students = studentRepository.search(keyword);

        Response<List<Student>> response = new Response<>(ResponseStatus.SUCCESS, "Get all students successfully", students);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
