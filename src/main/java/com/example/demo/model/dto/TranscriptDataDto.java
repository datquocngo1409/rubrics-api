package com.example.demo.model.dto;

import com.example.demo.model.StudentTotalRubricPoint;
import com.example.demo.model.TranscriptData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TranscriptDataDto {
    private Long id;
    private TranscriptInfomationDto transcriptInfomationDto;
    private ClassroomDto classroomDto;
    private List<StudentTotalRubricPointDto> studentTotalRubricPointDtoList;

    public TranscriptDataDto() {
    }

    public TranscriptDataDto(TranscriptData transcriptData) {
        this.id = transcriptData.getId();
        this.transcriptInfomationDto = new TranscriptInfomationDto(transcriptData.getTranscriptInnfomation());
        this.classroomDto = new ClassroomDto(transcriptData.getClassroom());
        this.studentTotalRubricPointDtoList = new ArrayList<>();
        if (transcriptData.getStudentTotalRubricPoints().size() > 0) {
            for (StudentTotalRubricPoint point : transcriptData.getStudentTotalRubricPoints()) {
                this.studentTotalRubricPointDtoList.add(new StudentTotalRubricPointDto(point));
            }
        } else {
            this.studentTotalRubricPointDtoList = new ArrayList<>();
        }
    }
}
