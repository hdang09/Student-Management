package hdang09.mappers;

import hdang09.dtos.requests.SubjectDTO;
import hdang09.entities.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    Subject toEntity(SubjectDTO studentDTO);
}
