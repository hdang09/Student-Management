package hdang09.services;

import hdang09.dtos.responses.StudentResponseDTO;
import hdang09.enums.ResponseStatus;
import hdang09.dtos.requests.StudentDTO;
import hdang09.models.Response;
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

    public ResponseEntity<Response<List<StudentResponseDTO>>> getAllStudents() {
        // Get all students from database
        List<Student> students = studentRepository.getActiveStudents();

        // Map students to StudentResponseDTO
        List<StudentResponseDTO> studentResponseDTOs = StudentMapper.INSTANCE.toDTOs(students);

        // Return response
        Response<List<StudentResponseDTO>> response = new Response<>(ResponseStatus.SUCCESS, "Get all students successfully", studentResponseDTOs);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Response<StudentResponseDTO>> createStudent(StudentDTO studentDTO) {
        // Save student to database
        Student student = StudentMapper.INSTANCE.toEntity(studentDTO);
        Student createdStudent = studentRepository.save(student);

        // Map created student to StudentResponseDTO
        StudentResponseDTO studentResponseDTO = StudentMapper.INSTANCE.toDTO(createdStudent);

        // Return response
        Response<StudentResponseDTO> response = new Response<>(ResponseStatus.SUCCESS, "Created", studentResponseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<Response<StudentResponseDTO>> updateStudent(StudentDTO studentDTO, UUID studentId) {
        // Check if student exists
        Student findStudent = studentRepository.findById(studentId).orElse(null);
        if (findStudent == null) {
            Response<StudentResponseDTO> response = new Response<>(ResponseStatus.ERROR, "Cannot find student");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Update student
        Student student = StudentMapper.INSTANCE.toEntity(studentDTO);
        student.setStudentId(studentId);
        Student updateStudent = studentRepository.save(student);

        // Map updated student to StudentResponseDTO
        StudentResponseDTO studentResponseDTO = StudentMapper.INSTANCE.toDTO(updateStudent);

        // Return response
        Response<StudentResponseDTO> response = new Response<>(ResponseStatus.SUCCESS, "Updated", studentResponseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // TODO: Fix Response raw type
    public ResponseEntity<Response> deleteStudent(UUID studentId) {
        // Delete student from database
        int numberOfDelete = studentRepository.deleteStudent(studentId);

        // If student is found, set status to INACTIVE
        if (numberOfDelete != 0) {
            Response response = new Response(ResponseStatus.SUCCESS, "Deleted");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        // If student is not found, return error
        Response response = new Response(ResponseStatus.ERROR, "Cannot find student");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public ResponseEntity<Response<List<StudentResponseDTO>>> getStudentsByKeyword(String keyword) {
        // Get students from database
        List<Student> students = studentRepository.search(keyword);

        // Map students to StudentResponseDTO
        List<StudentResponseDTO> studentResponseDTOs = StudentMapper.INSTANCE.toDTOs(students);

        // Return response
        Response<List<StudentResponseDTO>> response = new Response<>(ResponseStatus.SUCCESS, "Get all students successfully", studentResponseDTOs);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
