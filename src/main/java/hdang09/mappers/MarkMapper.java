package hdang09.mappers;

import hdang09.dtos.responses.MarkResponseDTO;
import hdang09.entities.Mark;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MarkMapper {
    MarkMapper INSTANCE = Mappers.getMapper(MarkMapper.class);

    MarkResponseDTO toDTO(Mark mark);

    List<MarkResponseDTO> toDTOs(List<Mark> marks);
}
