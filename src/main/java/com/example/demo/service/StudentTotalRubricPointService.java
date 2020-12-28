package com.example.demo.service;

import com.example.demo.model.StudentRubricPoint;
import com.example.demo.model.StudentTotalRubricPoint;
import com.example.demo.repository.StudentTotalRubricPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentTotalRubricPointService {
    @Autowired
    private StudentTotalRubricPointRepository repository;

    public List<StudentTotalRubricPoint> findAll() {
        return repository.findAll();
    }

    public StudentTotalRubricPoint findById(Long id) {
        return repository.findById(id).get();
    }

    public void create(StudentTotalRubricPoint model) {
        repository.save(model);
    }

    public void save(StudentTotalRubricPoint model) {
        repository.save(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public StudentTotalRubricPoint recalculator(StudentTotalRubricPoint strp) {
        if (strp.getStudentRubricPoint().size() == 0) {
            strp.setPassed(false);
            strp.setTotalPoint(0);
            return strp;
        }
        double avgPoint;
        double totalImportantPoint = 0;
        double totalPoint = 0;
        for (StudentRubricPoint srp : strp.getStudentRubricPoint()) {
            totalImportantPoint += srp.getRubricImportant().getImportant();
            totalPoint += srp.getPoint() * srp.getRubricImportant().getImportant();
        }
        avgPoint = totalPoint / totalImportantPoint;
        if (avgPoint > 4) {
            strp.setPassed(true);
        }
        strp.setTotalPoint(avgPoint);
        return strp;
    }
}
