package hdang09.controllers;

import hdang09.dtos.StudentCreateDTO;
import hdang09.entities.Response;
import hdang09.entities.Student;
import hdang09.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<Response<Student>> createStudent(@Valid @RequestBody StudentCreateDTO student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<Response> updateStudent(@RequestBody Student student, @PathVariable String studentId) {
        return studentService.updateStudent(student, studentId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteStudent(String studentId) {
        return studentService.deleteStudent(studentId);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<Response<List<Student>>> getStudentsByKeyword(@PathVariable String keyword) {
        return studentService.getStudentsByKeyword(keyword);
    }
}
