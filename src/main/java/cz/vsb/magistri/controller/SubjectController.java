package cz.vsb.magistri.controller;

import cz.vsb.magistri.dto.SubjectDto;
import cz.vsb.magistri.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @GetMapping("/subjects")
    public List<SubjectDto> getSubjects() {
        return subjectService.getSubject();
    }

    @GetMapping("subjects/{subjectId}")
    public SubjectDto getSubjects(@PathVariable int subjectId) {
        return subjectService.getSubjects(subjectId);
    }

    @PostMapping("/subjects")
    public SubjectDto addStudent(@RequestBody SubjectDto subjectDto) {
        return subjectService.addSubjects(subjectDto);
    }

    @PutMapping("/subjects/{subjectId}")
    public SubjectDto editSubject(@PathVariable int subjectId, @RequestBody SubjectDto subjectDto) {
        return subjectService.editSubject(subjectId, subjectDto);
    }

    @DeleteMapping("/subjects/{subjectId}")
    public SubjectDto deleteSubject(@PathVariable int subjectId) {
        return subjectService.deleteSubject(subjectId);
    }
}
