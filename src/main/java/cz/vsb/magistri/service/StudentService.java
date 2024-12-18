package cz.vsb.magistri.service;

import cz.vsb.magistri.dto.StudentDto;
import cz.vsb.magistri.entity.StudentEntity;
import cz.vsb.magistri.mapper.StudentMapper;
import cz.vsb.magistri.repository.ClassroomRepository;
import cz.vsb.magistri.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassroomRepository classroomRepository;

    public List<StudentDto> getStudents() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for (StudentEntity studentEntity : studentEntities) {
            studentDtos.add(studentMapper.toDto(studentEntity));
        }
    return studentDtos;
    }

    public StudentDto getStudent(int studentId){
        return studentMapper.toDto(studentRepository.getReferenceById(studentId));
    }

    public StudentDto addStudent(StudentDto studentDto) {
        StudentEntity studentEntity = studentMapper.toEntity(studentDto);
        studentEntity.setClassroom(classroomRepository.getReferenceById(studentDto.getClassroomId()));
        StudentEntity savedEntity = studentRepository.save(studentEntity);
        return studentMapper.toDto(savedEntity);
    }

    public StudentDto editStudent(int studentId, StudentDto studentDto) {
        if (!studentRepository.existsById(studentId)) {
            throw new EntityNotFoundException("Student with id " + studentId + " not found!");
        }
        StudentEntity studentEntity = studentMapper.toEntity(studentDto);
        studentEntity.setId(studentId);
        StudentEntity savedStudent = studentRepository.save(studentEntity);
        return studentMapper.toDto(savedStudent);
    }
    public StudentDto deleteStudent(int studentId) {
        StudentEntity studentEntity = studentRepository.findById(studentId).orElseThrow(EntityNotFoundException::new);
        StudentDto deletedStudent = studentMapper.toDto(studentEntity);
        studentRepository.delete(studentEntity);
        return deletedStudent;
    }
}
