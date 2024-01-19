package hdang09.controllers;

import hdang09.dtos.MarkDTO;
import hdang09.entities.Mark;
import hdang09.entities.Response;
import hdang09.services.MarkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mark")
@CrossOrigin
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

    @GetMapping("/subject/{subjectCode}")
    public ResponseEntity<Response<List<Mark>>> getMarksBySubjectCode(@PathVariable String subjectCode) {
        return markService.getMarksBySubjectCode(subjectCode);
    }
}
