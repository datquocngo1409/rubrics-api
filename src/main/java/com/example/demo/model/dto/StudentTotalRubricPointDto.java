package com.example.demo.model.dto;

import com.example.demo.model.StudentRubricPoint;
import com.example.demo.model.StudentTotalRubricPoint;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentTotalRubricPointDto {
    private Long id;
    private List<StudentRubricPointDto> studentRubricPointDtoList;
    private Long studentId;
    private double totalPoint;
    private String totalPointString;
    private double gpaPoint;
    private boolean isPassed;

    public StudentTotalRubricPointDto() {
    }

    public StudentTotalRubricPointDto(StudentTotalRubricPoint srp) {
        this.id = srp.getId();
        this.studentId = srp.getStudentId();
        this.totalPoint = srp.getTotalPoint();
        this.totalPointString = getTotalPointString(srp.getTotalPoint());
        this.gpaPoint = getGPA(srp.getTotalPoint());
        this.isPassed = srp.isPassed();
        this.studentRubricPointDtoList = new ArrayList<>();
        if (srp.getStudentRubricPoint().size() > 0) {
            for (StudentRubricPoint point : srp.getStudentRubricPoint()) {
                this.studentRubricPointDtoList.add(new StudentRubricPointDto(point));
            }
        }
    }

    private String getTotalPointString(double totalPoint) {
        if (totalPoint >= 9.0) {
            return "A+";
        } else if (totalPoint >= 8.5) {
            return "A";
        } else if (totalPoint >= 8) {
            return "B+";
        } else if (totalPoint >= 7) {
            return "B";
        } else if (totalPoint >= 6.5) {
            return "C+";
        } else if (totalPoint >= 5.5) {
            return "C";
        } else if (totalPoint >= 5) {
            return "D+";
        } else if (totalPoint > 4) {
            return "D";
        } else {
            return "F";
        }
    }

    private double getGPA(double totalPoint) {
        if (totalPoint >= 9.0) {
            return 4;
        } else if (totalPoint >= 8.5) {
            return 3.7;
        } else if (totalPoint >= 8) {
            return 3.5;
        } else if (totalPoint >= 7) {
            return 3;
        } else if (totalPoint >= 6.5) {
            return 2.5;
        } else if (totalPoint >= 5.5) {
            return 2;
        } else if (totalPoint >= 5) {
            return 1.5;
        } else if (totalPoint >= 4) {
            return 1;
        } else {
            return 0;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<StudentRubricPointDto> getStudentRubricPointDtoList() {
        return studentRubricPointDtoList;
    }

    public void setStudentRubricPointDtoList(List<StudentRubricPointDto> studentRubricPointDtoList) {
        this.studentRubricPointDtoList = studentRubricPointDtoList;
    }

    public double getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(double totalPoint) {
        this.totalPoint = totalPoint;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getTotalPointString() {
        return totalPointString;
    }

    public void setTotalPointString(String totalPointString) {
        this.totalPointString = totalPointString;
    }

    public double getGpaPoint() {
        return gpaPoint;
    }

    public void setGpaPoint(double gpaPoint) {
        this.gpaPoint = gpaPoint;
    }
}
