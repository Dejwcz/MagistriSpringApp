package cz.vsb.magistri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDto {
    private long id;
    private int studentId;
    private StudentDto student;
    private int subjectId;
    private SubjectDto subject;
    private int mark;
    private String topic;
}
