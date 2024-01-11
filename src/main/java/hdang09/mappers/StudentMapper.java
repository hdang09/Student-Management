package hdang09.mappers;

import hdang09.dtos.StudentCreateDTO;
import hdang09.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student toEntity(StudentCreateDTO studentDTO);
}
