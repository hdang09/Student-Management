package hdang09.services;

import hdang09.dtos.responses.SubjectResponseDTO;
import hdang09.enums.ResponseStatus;
import hdang09.dtos.requests.SubjectDTO;
import hdang09.models.Response;
import hdang09.entities.Subject;
import hdang09.mappers.SubjectMapper;
import hdang09.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public ResponseEntity<Response<List<SubjectResponseDTO>>> getAllSubjects() {
        // Get all subjects from database
        List<Subject> subjects = subjectRepository.getActiveSubjects();

        // Map subjects to SubjectResponseDTO
        List<SubjectResponseDTO> subjectResponseDTOs = SubjectMapper.INSTANCE.toDTOs(subjects);

        // Return response
        Response<List<SubjectResponseDTO>> response = new Response<>(ResponseStatus.SUCCESS, "Get all subjects successfully", subjectResponseDTOs);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Response<SubjectResponseDTO>> createSubject(SubjectDTO subjectDTO) {
        // Save subject to database
        Subject subject = SubjectMapper.INSTANCE.toEntity(subjectDTO);
        Subject createdSubject = subjectRepository.save(subject);

        // Map created subject to SubjectResponseDTO
        SubjectResponseDTO subjectResponseDTO = SubjectMapper.INSTANCE.toDTO(createdSubject);

        // Return response
        Response<SubjectResponseDTO> response = new Response<>(ResponseStatus.SUCCESS, "Created", subjectResponseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<Response<SubjectResponseDTO>> updateSubject(SubjectDTO subjectDTO, UUID subjectId) {
        // Check if subject exists
        Subject findSubject = subjectRepository.findById(subjectId).orElse(null);
        if (findSubject == null) {
            Response<SubjectResponseDTO> response = new Response<>(ResponseStatus.ERROR, "Cannot find subject");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Update subject
        Subject subject = SubjectMapper.INSTANCE.toEntity(subjectDTO);
        subject.setSubjectId(subjectId);
        Subject updateSubject = subjectRepository.save(subject);

        // Map updated subject to SubjectResponseDTO
        SubjectResponseDTO subjectResponseDTO = SubjectMapper.INSTANCE.toDTO(updateSubject);

        // Return response
        Response<SubjectResponseDTO> response = new Response<>(ResponseStatus.SUCCESS, "Subject updated!", subjectResponseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // TODO: Fix Response raw type
    public ResponseEntity<Response> deleteSubject(String subjectCode) {
        // Delete subject from database
        int numberOfDelete = subjectRepository.deleteSubject(subjectCode);

        // If subject is found, set status to INACTIVE
        if (numberOfDelete != 0) {
            Response response = new Response(ResponseStatus.SUCCESS, "Deleted");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        // If subject is not found, return error
        Response response = new Response(ResponseStatus.ERROR, "Cannot find subject");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public ResponseEntity<Response<List<SubjectResponseDTO>>> getSubjectsByKeyword(String keyword) {
        // Get all subjects from database
        List<Subject> subjects = subjectRepository.search(keyword);

        // Map subjects to SubjectResponseDTO
        List<SubjectResponseDTO> subjectResponseDTOs = SubjectMapper.INSTANCE.toDTOs(subjects);

        // Return response
        Response<List<SubjectResponseDTO>> response = new Response<>(ResponseStatus.SUCCESS, "Get all subject successfully", subjectResponseDTOs);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
