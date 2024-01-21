package hdang09.mappers;

import hdang09.dtos.requests.SubjectDTO;
import hdang09.dtos.responses.SubjectResponseDTO;
import hdang09.entities.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    Subject toEntity(SubjectDTO studentDTO);

    SubjectResponseDTO toDTO(Subject student);

    List<SubjectResponseDTO> toDTOs(List<Subject> subjects);
}
