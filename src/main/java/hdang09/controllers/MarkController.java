package hdang09.controllers;

import hdang09.entities.Mark;
import hdang09.entities.Response;
import hdang09.services.MarkService;
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
}
