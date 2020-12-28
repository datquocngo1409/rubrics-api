package com.example.demo.model.dto;

import com.example.demo.model.Classroom;
import com.example.demo.model.StudentTotalRubricPoint;
import com.example.demo.model.TranscriptData;
import com.example.demo.service.TranscriptDataService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClassroomDataDto {
    private Long id;
    private ClassroomDto classroomDto;
    private List<StudentTotalRubricPointDto> studentTotalRubricPointDtos;

    public ClassroomDataDto(Classroom classroom, TranscriptData transcriptData) {
        this.id = classroom.getId();
        this.classroomDto = new ClassroomDto(classroom);
        this.studentTotalRubricPointDtos = new ArrayList<>();
        for (StudentTotalRubricPoint strp : transcriptData.getStudentTotalRubricPoints()) {
            StudentTotalRubricPointDto strpDto = new StudentTotalRubricPointDto(strp);
            this.studentTotalRubricPointDtos.add(strpDto);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassroomDto getClassroomDto() {
        return classroomDto;
    }

    public void setClassroomDto(ClassroomDto classroomDto) {
        this.classroomDto = classroomDto;
    }

    public List<StudentTotalRubricPointDto> getStudentTotalRubricPointDtos() {
        return studentTotalRubricPointDtos;
    }

    public void setStudentTotalRubricPointDtos(List<StudentTotalRubricPointDto> studentTotalRubricPointDtos) {
        this.studentTotalRubricPointDtos = studentTotalRubricPointDtos;
    }
}
