package hdang09.services;

import hdang09.dtos.responses.MarkResponseDTO;
import hdang09.entities.MarkId;
import hdang09.enums.ResponseStatus;
import hdang09.dtos.requests.MarkDTO;
import hdang09.dtos.requests.MarkDeleteDTO;
import hdang09.dtos.requests.MarkUpdateDTO;
import hdang09.entities.Mark;
import hdang09.mappers.MarkMapper;
import hdang09.models.Response;
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

    public ResponseEntity<Response<List<MarkResponseDTO>>> getAllMarks() {
        // Get all marks
        List<Mark> marks = markRepository.findAll();

        // Map marks to MarkResponseDTO
        List<MarkResponseDTO> markResponseDTOs = MarkMapper.INSTANCE.toDTOs(marks);

        // Return response
        Response<List<MarkResponseDTO>> response = new Response<>(ResponseStatus.SUCCESS, "Get all marks successfully", markResponseDTOs);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Response<MarkResponseDTO>> addMark(MarkDTO markDTO) {
        // Find student
        Student student = studentRepository.findByRollNumber(markDTO.getRollNumber());
        if (student == null) {
            Response<MarkResponseDTO> response = new Response<>(ResponseStatus.ERROR, "Student not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Find subject
        Subject subject =  subjectRepository.findBySubjectCode(markDTO.getSubjectCode());
        if (subject == null) {
            Response<MarkResponseDTO> response = new Response<>(ResponseStatus.ERROR, "Subject not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Create mark
        Mark mark = new Mark(student, subject, markDTO.getMark(), LocalDateTime.now(), LocalDateTime.now(), markDTO.getNote());
        Mark savedMark = markRepository.save(mark);

        // Map saved mark to MarkResponseDTO
        MarkResponseDTO markResponseDTO = MarkMapper.INSTANCE.toDTO(savedMark);

        // Return response
        Response<MarkResponseDTO> response = new Response<>(ResponseStatus.SUCCESS, "Add mark successfully", markResponseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // TODO: Fix bug this function
    public ResponseEntity<Response> updateMark(MarkUpdateDTO markUpdateDTO, String rollNumber, String subjectCode) {
        // Find student
        List<Mark> students = markRepository.findByRollNumber(rollNumber);
        if (students == null) {
            Response response = new Response<>(ResponseStatus.ERROR, "Student not found in mark list");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Find subject
        Subject subject =  subjectRepository.findBySubjectCode(subjectCode);
        if (subject == null) {
            Response response = new Response<>(ResponseStatus.ERROR, "Subject not found in mark list");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Update mark
        int updatedRows = markRepository.updateMark(rollNumber, subjectCode, markUpdateDTO.getMark(), markUpdateDTO.getNote());
        if (updatedRows == 0) {
            Response response = new Response<>(ResponseStatus.ERROR, "Update mark failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Return response
        Response response = new Response<>(ResponseStatus.SUCCESS, "Update mark successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Response> deleteMark(MarkDeleteDTO markDeleteDTO) {
        // Delete mark
        markRepository.deleteMark(markDeleteDTO.getRollNumber(), markDeleteDTO.getSubjectCode());

        // Return response
        Response response = new Response(ResponseStatus.SUCCESS, "Delete mark successfully", null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Response<List<MarkResponseDTO>>> getMarksByRollNumber(String rollNumber) {
        // Get marks from database
        List<Mark> marks = markRepository.findByRollNumber(rollNumber);

        // Map marks to MarkResponseDTO
        List<MarkResponseDTO> markResponseDTOs = MarkMapper.INSTANCE.toDTOs(marks);

        // Return response
        Response<List<MarkResponseDTO>> response = new Response<>(ResponseStatus.SUCCESS, "Get all students successfully", markResponseDTOs);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Response<List<MarkResponseDTO>>> getMarksBySubjectCode(String subjectCode) {
        // Find subject
        Subject subject = subjectRepository.findBySubjectCode(subjectCode);
        if (subject == null) {
            Response<List<MarkResponseDTO>> response = new Response<>(ResponseStatus.ERROR, "Subject not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Get marks by subject
        List<Mark> marks = markRepository.findBySubject(subject);

        // Map marks to MarkResponseDTO
        List<MarkResponseDTO> markResponseDTOs = MarkMapper.INSTANCE.toDTOs(marks);

        // Return response
        Response<List<MarkResponseDTO>> response = new Response<>(ResponseStatus.SUCCESS, "Get marks by subject successfully", markResponseDTOs);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
