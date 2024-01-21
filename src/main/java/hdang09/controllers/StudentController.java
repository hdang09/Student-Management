package hdang09.controllers;

import hdang09.dtos.requests.StudentDTO;
import hdang09.dtos.responses.StudentResponseDTO;
import hdang09.models.Response;
import hdang09.services.StudentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin
@Tag(name = "Student")
@SecurityRequirement(name = "bearerAuth")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<Response<List<StudentResponseDTO>>> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/create")
    public ResponseEntity<Response<StudentResponseDTO>> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<Response<StudentResponseDTO>> updateStudent(
            @Valid @RequestBody StudentDTO studentDTO, @PathVariable UUID studentId
    ) {
        return studentService.updateStudent(studentDTO, studentId);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<Response> deleteStudent(@PathVariable UUID studentId) {
        return studentService.deleteStudent(studentId);
    }

    @GetMapping("/search")
    public ResponseEntity<Response<List<StudentResponseDTO>>> getStudentsByKeyword(@RequestParam String keyword) {
        return studentService.getStudentsByKeyword(keyword);
    }
}
