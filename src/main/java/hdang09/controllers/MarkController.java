package hdang09.controllers;

import hdang09.dtos.requests.MarkDTO;
import hdang09.dtos.requests.MarkDeleteDTO;
import hdang09.dtos.requests.MarkUpdateDTO;
import hdang09.entities.Mark;
import hdang09.models.Response;
import hdang09.services.MarkService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mark")
@CrossOrigin
@Tag(name = "Mark")
@SecurityRequirement(name = "bearerAuth")
public class MarkController {

    private final MarkService markService;

    @Autowired
    public MarkController(MarkService markService){
        this.markService = markService;
    }

    @GetMapping("/all")
    public ResponseEntity<Response<List<Mark>>> getAllMarks(){
        return markService.getAllMarks();
    }

    @PostMapping("/add")
    public ResponseEntity<Response<Mark>> addMark(@Valid @RequestBody MarkDTO markDTO) {
        return markService.addMark(markDTO);
    }

    @PutMapping("/update/{rollNumber}/{subjectCode}")
    public ResponseEntity<Response<Mark>> updateMark(
            @Valid @RequestBody MarkUpdateDTO markUpdateDTO, @PathVariable String rollNumber, @PathVariable String subjectCode
    ) {
        return markService.updateMark(markUpdateDTO, rollNumber, subjectCode);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteMark(@RequestBody MarkDeleteDTO markDeleteDTO) {
        return markService.deleteMark(markDeleteDTO);
    }

    @GetMapping("/search/{rollNumber}")
    public ResponseEntity<Response<List<Mark>>> search(@PathVariable String rollNumber) {
        return markService.getMarksByRollNumber(rollNumber);
    }

    @GetMapping("/subject/{subjectCode}")
    public ResponseEntity<Response<List<Mark>>> getMarksBySubjectCode(@PathVariable String subjectCode) {
        return markService.getMarksBySubjectCode(subjectCode);
    }
}
