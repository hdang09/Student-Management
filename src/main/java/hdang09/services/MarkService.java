package hdang09.services;

import hdang09.constants.ResponseStatus;
import hdang09.entities.Mark;
import hdang09.entities.Response;
import hdang09.repositories.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MarkService {

    private final MarkRepository markRepository;

    @Autowired
    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public ResponseEntity<Response<List<Mark>>> getAllMarks() {
        // Add data to test
//        MarkId markId = new MarkId(studentRepository.findAll().get(1), subjectRepository.findAll().get(0));
//        Mark mark = new Mark(markId, 10, LocalDateTime.now(), "Good");
//        markRepository.save(mark);

        // Get all marks
        List<Mark> marks = markRepository.findAll();

        Response<List<Mark>> response = new Response<>(ResponseStatus.SUCCESS, "Get all marks successfully", marks);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
