package cz.vsb.magistri.service;

import cz.vsb.magistri.dto.StudentDto;
import cz.vsb.magistri.dto.SubjectDto;
import cz.vsb.magistri.entity.SubjectEntity;
import cz.vsb.magistri.mapper.SubjectMapper;
import cz.vsb.magistri.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private SubjectRepository subjectRepository;

    public List<SubjectDto> getSubject() {
        List<SubjectEntity> subjectEntities = subjectRepository.findAll();
        List<SubjectDto> subjectDtos = new ArrayList<>();
        for (SubjectEntity subjectEntity : subjectEntities) {
            subjectDtos.add(subjectMapper.toDto(subjectEntity));
        }
        return subjectDtos;
    }
    public SubjectDto getSubjects(int subjectId) {
        return subjectMapper.toDto(subjectRepository.getReferenceById(subjectId));
    }

    public SubjectDto addSubjects(SubjectDto subjectDto) {
        SubjectEntity subjectEntity = subjectMapper.toEntity(subjectDto);
        SubjectEntity savedSubject = subjectRepository.save(subjectEntity);
        return subjectMapper.toDto(savedSubject);
    }

    public SubjectDto editSubject(int subjectId, SubjectDto subjectDto) {
        subjectRepository.findById(subjectId).orElseThrow(EntityNotFoundException::new);
        SubjectEntity subjectEntity = subjectMapper.toEntity(subjectDto);
        subjectEntity.setId(subjectId);
        SubjectEntity editedSubject = subjectRepository.save(subjectEntity);
        return subjectMapper.toDto(editedSubject);
    }

    public SubjectDto deleteSubject(int subjectId) {
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElseThrow(EntityNotFoundException::new);
        SubjectDto deletedSubject = subjectMapper.toDto(subjectEntity);
        subjectRepository.delete(subjectEntity);
        return deletedSubject;
    }
}

