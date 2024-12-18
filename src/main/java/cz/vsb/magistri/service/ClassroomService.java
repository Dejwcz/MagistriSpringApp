package cz.vsb.magistri.service;

import cz.vsb.magistri.dto.ClassroomDto;
import cz.vsb.magistri.entity.ClassroomEntity;
import cz.vsb.magistri.mapper.ClassroomMapper;
import cz.vsb.magistri.repository.ClassroomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomMapper classroomMapper;
    @Autowired
    private ClassroomRepository classroomRepository;

    public List<ClassroomDto> getClassrooms() {
        List<ClassroomEntity> classroomEntities = classroomRepository.findAll();
        List<ClassroomDto> classroomDtos = new ArrayList<>();
        for (ClassroomEntity classroomEntity : classroomEntities) {
            classroomDtos.add(classroomMapper.toDto(classroomEntity));
        }
        return classroomDtos;
    }

    public ClassroomDto addClassroom(ClassroomDto classroomDto) {
        ClassroomEntity classroomEntity = classroomMapper.toEntity(classroomDto);
        ClassroomEntity classroomSaved = classroomRepository.save(classroomEntity);
        return classroomMapper.toDto(classroomSaved);
    }

    public ClassroomDto editClassroom(int classroomId, ClassroomDto classroomDto) {
        ClassroomEntity classroomEntity = classroomRepository.findById(classroomId).orElseThrow(EntityNotFoundException::new);
        classroomEntity = classroomMapper.toEntity(classroomDto);
        classroomEntity.setId(classroomId);
        ClassroomEntity editedClassroom = classroomRepository.save(classroomEntity);
        return classroomMapper.toDto(editedClassroom);
    }

    public ClassroomDto deleteClassroom(int classroomId) {
        ClassroomEntity classroomEntity = classroomRepository.findById(classroomId).orElseThrow(EntityNotFoundException::new);
        ClassroomDto deletedClassroom = classroomMapper.toDto(classroomEntity);
        classroomRepository.delete(classroomEntity);
        return deletedClassroom;
    }
}
