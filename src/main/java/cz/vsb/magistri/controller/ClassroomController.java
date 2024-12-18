package cz.vsb.magistri.controller;

import cz.vsb.magistri.dto.ClassroomDto;
import cz.vsb.magistri.entity.ClassroomEntity;
import cz.vsb.magistri.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ClassroomController {
    @Autowired
    ClassroomService classroomService;

    @GetMapping("/classrooms")
    public List<ClassroomDto> getClassroooms() {
        return classroomService.getClassrooms();
    }
    @PostMapping("/classrooms")
    public ClassroomDto addClassroom(@RequestBody ClassroomDto classroomDto) {
        return classroomService.addClassroom(classroomDto);
    }
    @PutMapping("/classrooms/{classroomId}")
    public ClassroomDto editClassroom(@PathVariable int classroomId, @RequestBody ClassroomDto classroomDto){
        return classroomService.editClassroom(classroomId, classroomDto);
    }
    @DeleteMapping("classrooms/{classroomId}")
    public ClassroomDto deleteClassroom(@PathVariable int classroomId) {
        return classroomService.deleteClassroom(classroomId);
    }
}

