package hdang09.controllers;

import hdang09.dtos.StudentDTO;
import hdang09.entities.Response;
import hdang09.entities.Student;
import hdang09.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<Response<List<Student>>> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/create")
    public ResponseEntity<Response<Student>> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<Response<Student>> updateStudent(@Valid @RequestBody StudentDTO studentDTO, @PathVariable UUID studentId) {
        return studentService.updateStudent(studentDTO, studentId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteStudent(String rollNumber) {
        return studentService.deleteStudent(rollNumber);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<Response<List<Student>>> getStudentsByKeyword(@PathVariable String keyword) {
        return studentService.getStudentsByKeyword(keyword);
    }
}
