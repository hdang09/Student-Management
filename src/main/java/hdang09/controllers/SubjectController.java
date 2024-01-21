package hdang09.controllers;

import hdang09.dtos.requests.SubjectDTO;
import hdang09.dtos.responses.SubjectResponseDTO;
import hdang09.models.Response;
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
    public ResponseEntity<Response<List<SubjectResponseDTO>>> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping("/create")
    public ResponseEntity<Response<SubjectResponseDTO>> createSubject(@Valid @RequestBody SubjectDTO studentDTO) {
        return subjectService.createSubject(studentDTO);
    }

    @PutMapping("/update/{subjectId}")
    public ResponseEntity<Response<SubjectResponseDTO>> updateSubject(@Valid @RequestBody SubjectDTO studentDTO, @PathVariable UUID subjectId) {
        return subjectService.updateSubject(studentDTO, subjectId);
    }

    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity<Response> deleteSubject(@PathVariable UUID subjectId) {
        return subjectService.deleteSubject(subjectId);
    }

    @GetMapping("/search")
    public ResponseEntity<Response<List<SubjectResponseDTO>>> getSubjectsByKeyword(@RequestParam String keyword) {
        return subjectService.getSubjectsByKeyword(keyword);
    }
}
