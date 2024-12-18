package cz.vsb.magistri.service;

import cz.vsb.magistri.dto.GradeDto;
import cz.vsb.magistri.entity.GradeEntity;
import cz.vsb.magistri.mapper.GradeMapper;
import cz.vsb.magistri.repository.GradeRepository;
import cz.vsb.magistri.repository.StudentRepository;
import cz.vsb.magistri.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService {
    @Autowired
    GradeMapper gradeMapper;
    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;

    public List<GradeDto> getAllGrades() {
        List<GradeDto> gradeDtos = new ArrayList<>();
        for (GradeEntity gradeEntity : gradeRepository.findAll()) {
            gradeDtos.add(gradeMapper.toDto(gradeEntity));
        }
        return gradeDtos;
    }
    public GradeDto getGrade(long gradeId) {
        return gradeMapper.toDto(gradeRepository.getReferenceById(gradeId));
    }

    public GradeDto addGrade(GradeDto gradeDto){
        GradeEntity gradeEntity = gradeMapper.toEntity(gradeDto);
        gradeEntity.setStudent(studentRepository.getReferenceById(gradeDto.getStudentId()));
        gradeEntity.setSubject(subjectRepository.getReferenceById(gradeDto.getSubjectId()));
        return gradeMapper.toDto(gradeRepository.save(gradeEntity));
    }

    public GradeDto editGrade(GradeDto gradeDto, long gradeId){
        gradeDto.setId(gradeId);
        GradeEntity gradeToEdit = gradeRepository.getReferenceById(gradeId);
        gradeMapper.updateEntity(gradeDto, gradeToEdit);
        gradeToEdit.setSubject(subjectRepository.getReferenceById(gradeDto.getSubjectId()));
        gradeToEdit.setStudent(studentRepository.getReferenceById(gradeDto.getStudentId()));
        return gradeMapper.toDto(gradeRepository.save(gradeToEdit));
    }

    public GradeDto deleteGrade(long gradeId) {
        GradeEntity gradeToRemove = gradeRepository.getReferenceById(gradeId);
        GradeDto deletedGrade = gradeMapper.toDto(gradeToRemove);
        gradeRepository.delete(gradeToRemove);
        return deletedGrade;
    }
}
