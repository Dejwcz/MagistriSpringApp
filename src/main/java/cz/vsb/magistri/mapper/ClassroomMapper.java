package cz.vsb.magistri.mapper;

import cz.vsb.magistri.dto.ClassroomDto;
import cz.vsb.magistri.entity.ClassroomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {
    ClassroomEntity toEntity(ClassroomDto source);
    ClassroomDto toDto(ClassroomEntity source);
}
