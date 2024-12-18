package cz.vsb.magistri.mapper;

import cz.vsb.magistri.dto.StudentDto;
import cz.vsb.magistri.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ClassroomMapper.class)
public interface StudentMapper {
    StudentEntity toEntity(StudentDto source);
    @Mapping(target = "classroomId", source = "classroom.id")
    StudentDto toDto(StudentEntity source);
}
