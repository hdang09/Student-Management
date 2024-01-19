package hdang09.services;

import hdang09.constants.ResponseStatus;
import hdang09.dtos.MarkDTO;
import hdang09.entities.Mark;
import hdang09.entities.Response;
import hdang09.entities.Student;
import hdang09.entities.Subject;
import hdang09.repositories.MarkRepository;
import hdang09.repositories.StudentRepository;
import hdang09.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class MarkService {

    private final MarkRepository markRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public MarkService(MarkRepository markRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.markRepository = markRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public ResponseEntity<Response<List<Mark>>> getAllMarks() {
        // Get all marks
        List<Mark> marks = markRepository.findAll();

        Response<List<Mark>> response = new Response<>(ResponseStatus.SUCCESS, "Get all marks successfully", marks);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Response<Mark>> addMark(MarkDTO markDTO) {
        // Find student
        Student student = studentRepository.findByRollNumber(markDTO.getRollNumber());
        if (student == null) {
            Response<Mark> response = new Response<>(ResponseStatus.ERROR, "Student not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Find subject
        Subject subject =  subjectRepository.findBySubjectCode(markDTO.getSubjectCode());
        if (subject == null) {
            Response<Mark> response = new Response<>(ResponseStatus.ERROR, "Subject not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Create mark
        Mark mark = new Mark(student, subject, markDTO.getMark(), LocalDateTime.now(), LocalDateTime.now(), markDTO.getNote());
        Mark savedMark = markRepository.save(mark);

        Response<Mark> response = new Response<>(ResponseStatus.SUCCESS, "Add mark successfully", savedMark);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Response<List<Mark>>> getMarksBySubjectCode(String subjectCode) {
        // Find subject
        Subject subject = subjectRepository.findBySubjectCode(subjectCode);
        if (subject == null) {
            Response<List<Mark>> response = new Response<>(ResponseStatus.ERROR, "Subject not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Get marks by subject
        List<Mark> marks = markRepository.findBySubject(subject);

        Response<List<Mark>> response = new Response<>(ResponseStatus.SUCCESS, "Get marks by subject successfully", marks);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
