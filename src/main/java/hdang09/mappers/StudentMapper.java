package hdang09.mappers;

import hdang09.dtos.requests.StudentDTO;
import hdang09.dtos.responses.StudentResponseDTO;
import hdang09.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "gender", target = "gender")
    Student toEntity(StudentDTO studentDTO);

    StudentResponseDTO toDTO(Student student);

    List<StudentResponseDTO> toDTOs(List<Student> students);
}
