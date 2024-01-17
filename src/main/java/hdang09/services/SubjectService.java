package hdang09.services;

import hdang09.constants.ResponseStatus;
import hdang09.dtos.SubjectDTO;
import hdang09.entities.Response;
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

    public ResponseEntity<Response<List<Subject>>> getAllSubjects() {
        List<Subject> subjects = subjectRepository.getActiveSubjects();

        Response<List<Subject>> response = new Response<>(ResponseStatus.SUCCESS, "Get all subjects successfully", subjects);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Response<Subject>> createSubject(SubjectDTO subjectDTO) {
        // Save subject to database
        Subject subject = SubjectMapper.INSTANCE.toEntity(subjectDTO);
        Subject createdSubject = subjectRepository.save(subject);

        Response<Subject> response = new Response<>(ResponseStatus.SUCCESS, "Created", createdSubject);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // TODO: Test this function
    public ResponseEntity<Response<Subject>> updateSubject(SubjectDTO subjectDTO, UUID subjectId) {
        // Check if subject exists
        Subject findSubject = subjectRepository.findById(subjectId).orElse(null);
        if (findSubject == null) {
            Response<Subject> response = new Response<>(ResponseStatus.ERROR, "Cannot find subject");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Update subject
        Subject subject = SubjectMapper.INSTANCE.toEntity(subjectDTO);
        subject.setSubjectId(subjectId);
        Subject updateSubject = subjectRepository.save(subject);

        Response<Subject> response = new Response<>(ResponseStatus.SUCCESS, "Subject updated!", updateSubject);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // TODO: Fix Response raw type
    public ResponseEntity<Response> deleteSubject(String subjectCode) {
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

    public ResponseEntity<Response<List<Subject>>> getSubjectsByKeyword(String keyword) {
        List<Subject> subjects = subjectRepository.search(keyword);

        Response<List<Subject>> response = new Response<>(ResponseStatus.SUCCESS, "Get all subject successfully", subjects);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
