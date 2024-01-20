package hdang09.controllers;

import hdang09.dtos.requests.SubjectDTO;
import hdang09.models.Response;
import hdang09.entities.Subject;
import hdang09.services.SubjectService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subject")
@CrossOrigin
@Tag(name = "Subject")
@SecurityRequirement(name = "bearerAuth")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @GetMapping("/all")
    public ResponseEntity<Response<List<Subject>>> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping("/create")
    public ResponseEntity<Response<Subject>> createSubject(@Valid @RequestBody SubjectDTO studentDTO) {
        return subjectService.createSubject(studentDTO);
    }

    @PutMapping("/update/{subjectId}")
    public ResponseEntity<Response<Subject>> updateSubject(@Valid @RequestBody SubjectDTO studentDTO, @PathVariable UUID subjectId) {
        return subjectService.updateSubject(studentDTO, subjectId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteSubject(String subjectCode) {
        return subjectService.deleteSubject(subjectCode);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<Response<List<Subject>>> getSubjectsByKeyword(@PathVariable String keyword) {
        return subjectService.getSubjectsByKeyword(keyword);
    }
}
