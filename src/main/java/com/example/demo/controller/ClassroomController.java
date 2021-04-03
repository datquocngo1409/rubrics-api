package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.model.dto.*;
import com.example.demo.model.request.UpdateImportantRequest;
import com.example.demo.model.request.UpdatePointRequest;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class ClassroomController {
    @Autowired
    public ClassroomService service;
    @Autowired
    public UserService userService;
    @Autowired
    public TranscriptInfomationService transcriptInfomationService;
    @Autowired
    public TranscriptDataService transcriptDataService;
    @Autowired
    public RubricService rubricService;
    @Autowired
    public RubricImportantService rubricImportantService;
    @Autowired
    public StudentRubricPointService studentRubricPointService;
    @Autowired
    public StudentTotalRubricPointService studentTotalRubricPointService;

    //Get List Classroom
    @RequestMapping(value = "/classroom", method = RequestMethod.GET)
    public ResponseEntity<List<ClassroomDto>> listAll() {
        List<Classroom> accounts = service.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<ClassroomDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<ClassroomDto> dtos = new ArrayList<>();
        for (Classroom classroom : accounts) {
            ClassroomDto dto = new ClassroomDto(classroom);
            dtos.add(dto);
        }
        return new ResponseEntity<List<ClassroomDto>>(dtos, HttpStatus.OK);
    }

    //Get Classroom By Id
    @RequestMapping(value = "/classroom/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClassroomDto> getById(@PathVariable("id") Long id) {
        Classroom account = service.findById(id);
        if (account == null) {
            return new ResponseEntity<ClassroomDto>(HttpStatus.NOT_FOUND);
        }
        ClassroomDto dto = new ClassroomDto(account);
        return new ResponseEntity<ClassroomDto>(dto, HttpStatus.OK);
    }

    //Create Classroom
    @RequestMapping(value = "/classroom/{teacherId}", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Classroom classroom, UriComponentsBuilder ucBuilder, @PathVariable("teacherId") Long teacherId) {
        User teacher = userService.findById(teacherId);
        classroom.setTeacher(teacher);
        service.save(classroom);

        // Create Transcript Infomation
        TranscriptInfomation transcriptInfomation = new TranscriptInfomation();
        transcriptInfomation.setClassroom(classroom);
        transcriptInfomation.setRubricImportantList(new ArrayList<>());
        transcriptInfomationService.save(transcriptInfomation);

        // Create Transcript Data
        TranscriptData transcriptData = new TranscriptData();
        transcriptData.setClassroom(classroom);
        transcriptData.setTranscriptInnfomation(transcriptInfomation);
        transcriptData.setStudentTotalRubricPoints(new ArrayList<>());
        transcriptDataService.save(transcriptData);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/classroom/{id}").buildAndExpand(classroom.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Update Classroom
    @RequestMapping(value = "/classroom/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ClassroomDto> updateAdmin(@PathVariable("id") Long id, @RequestBody Classroom classroom) {
        Classroom current = service.findById(id);

        if (current == null) {
            return new ResponseEntity<ClassroomDto>(HttpStatus.NOT_FOUND);
        }

        classroom.setStudents(current.getStudents());
        classroom.setTeacher(current.getTeacher());
        current = classroom;

        service.save(current);
        ClassroomDto dto = new ClassroomDto(current);
        return new ResponseEntity<ClassroomDto>(dto, HttpStatus.OK);
    }

    //Delete Classroom
    @RequestMapping(value = "/classroom/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Classroom> delete(@PathVariable("id") Long id) {
        Classroom classroom = service.findById(id);
        if (classroom == null) {
            return new ResponseEntity<Classroom>(HttpStatus.NOT_FOUND);
        }

        service.delete(id);
        return new ResponseEntity<Classroom>(HttpStatus.NO_CONTENT);
    }

    //Update Teacher in Classroom
    @RequestMapping(value = "/classroom/updateTeacher/{id}/{teacherId}", method = RequestMethod.PATCH)
    public ResponseEntity<ClassroomDto> updateTeacherInClassroom(@PathVariable("id") Long id, @PathVariable("teacherId") Long teacherId) {
        Classroom classroom = service.findById(id);
        if (classroom == null) {
            return new ResponseEntity<ClassroomDto>(HttpStatus.NOT_FOUND);
        }
        User teacher = userService.findById(teacherId);
        if (teacher == null || !teacher.getRole().equals("TEACHER")) {
            return new ResponseEntity<ClassroomDto>(HttpStatus.NOT_FOUND);
        }
        classroom.setTeacher(teacher);

        service.save(classroom);
        return new ResponseEntity<ClassroomDto>(new ClassroomDto(classroom), HttpStatus.OK);
    }

    //Add Some Student to Classroom
    @RequestMapping(value = "/classroom/addStudents/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ClassroomDto> addStudent(@PathVariable("id") Long id, @RequestBody Map<String, String> parameters) {
        Classroom classroom = service.findById(id);
        if (classroom == null) {
            return new ResponseEntity<ClassroomDto>(HttpStatus.NOT_FOUND);
        }
        String studentIdList = parameters.get("studentIdList");
        String[] studentIdArray = studentIdList.split(",");
        TranscriptInfomation transcriptInfomation = transcriptInfomationService.findByClassroom(classroom);
        TranscriptData transcriptData = transcriptDataService.findByClassroom(classroom);

        List<StudentTotalRubricPoint> studentTotalRubricPoints = transcriptData.getStudentTotalRubricPoints();
        if (studentTotalRubricPoints == null) {
            studentTotalRubricPoints = new ArrayList<>();
        }

        List<RubricImportant> rubricImportantList = transcriptInfomation.getRubricImportantList();

        List<User> students = classroom.getStudents();
        if (students == null) {
            students = new ArrayList<>();
        }
        for (String studentIdString : studentIdArray) {
            Long studentId = Long.parseLong(studentIdString);
            User student = userService.findById(studentId);
            if (student == null || !student.getRole().equals("STUDENT")) {
                continue;
            }
            if (students.contains(student)) {
                continue;
            }
            List<StudentRubricPoint> studentRubricPoints = new ArrayList<>();
            for (RubricImportant rubricImportant : rubricImportantList) {
                StudentRubricPoint studentRubricPoint = new StudentRubricPoint(student, rubricImportant, 0);
                studentRubricPointService.save(studentRubricPoint);
                studentRubricPoints.add(studentRubricPoint);
            }
            StudentTotalRubricPoint studentTotalRubricPoint = new StudentTotalRubricPoint(studentRubricPoints, 0, false, studentId);
            studentTotalRubricPointService.save(studentTotalRubricPoint);
            studentTotalRubricPoints.add(studentTotalRubricPoint);
            students.add(student);
        }
        transcriptData.setStudentTotalRubricPoints(studentTotalRubricPoints);
        transcriptDataService.save(transcriptData);
        classroom.setStudents(students);
        service.save(classroom);
        return new ResponseEntity<ClassroomDto>(new ClassroomDto(classroom), HttpStatus.OK);
    }

    //Add Some Student to Classroom
    @RequestMapping(value = "/classroom/deleteStudents/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ClassroomDto> deleteStudent(@PathVariable("id") Long id, @RequestBody Map<String, String> parameters) {
        Classroom classroom = service.findById(id);
        if (classroom == null) {
            return new ResponseEntity<ClassroomDto>(HttpStatus.NOT_FOUND);
        }
        String studentIdList = parameters.get("studentIdList");
        String[] studentIdArray = studentIdList.split(",");
        List<User> students = classroom.getStudents();
        if (students == null) {
            students = new ArrayList<>();
            return new ResponseEntity<ClassroomDto>(new ClassroomDto(classroom), HttpStatus.OK);
        }
        TranscriptData transcriptData = transcriptDataService.findByClassroom(classroom);
        for (String studentIdString : studentIdArray) {
            Long studentId = Long.parseLong(studentIdString);
            User student = userService.findById(studentId);
            if (student == null || !student.getRole().equals("STUDENT")) {
                continue;
            }
            if (!students.contains(student)) {
                continue;
            }
            for (int i = 0; i < transcriptData.getStudentTotalRubricPoints().size(); i++) {
                StudentTotalRubricPoint strp = transcriptData.getStudentTotalRubricPoints().get(i);
                if (strp.getStudentId().equals(student.getId())) {
                    List<StudentTotalRubricPoint> studentTotalRubricPoints = transcriptData.getStudentTotalRubricPoints();
                    studentTotalRubricPoints.remove(strp);
                    transcriptData.setStudentTotalRubricPoints(studentTotalRubricPoints);
                    for (StudentRubricPoint srp : strp.getStudentRubricPoint()) {
//                        studentRubricPointService.delete(srp.getId());
                    }
                    transcriptDataService.save(transcriptData);
                    studentTotalRubricPointService.delete(strp.getId());
//                    i++;
                }
            }
            students.remove(student);
        }
        classroom.setStudents(students);
        service.save(classroom);
        return new ResponseEntity<ClassroomDto>(new ClassroomDto(classroom), HttpStatus.OK);
    }

    @RequestMapping(value = "/classroom/updateName/{id}/{name}", method = RequestMethod.PATCH)
    public ResponseEntity<ClassroomDto> updateName(@PathVariable("id") Long id, @PathVariable("name") String name) {
        Classroom classroom = service.findById(id);
        if (classroom == null) {
            return new ResponseEntity<ClassroomDto>(HttpStatus.NOT_FOUND);
        }
        classroom.setName(name);
        service.save(classroom);
        return new ResponseEntity<ClassroomDto>(new ClassroomDto(classroom), HttpStatus.OK);
    }

    @RequestMapping(value = "/classroom/getRubrics/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Rubric>> getRubrics(@PathVariable("id") Long id) {
        Classroom classroom = service.findById(id);
        if (classroom == null) {
            return new ResponseEntity<List<Rubric>>(HttpStatus.NOT_FOUND);
        }
        TranscriptInfomation transcriptInfomation = transcriptInfomationService.findByClassroom(classroom);
        List<Rubric> rubrics = new ArrayList<>();
        for (RubricImportant ri : transcriptInfomation.getRubricImportantList()) {
            rubrics.add(ri.getRubric());
        }
        if (rubrics.size() == 0) {
            return new ResponseEntity<List<Rubric>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Rubric>>(rubrics, HttpStatus.OK);
    }

    @RequestMapping(value = "/classroom/getRubricImportants/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<RubricImportantDto>> getRubricImportants(@PathVariable("id") Long id) {
        Classroom classroom = service.findById(id);
        if (classroom == null) {
            return new ResponseEntity<List<RubricImportantDto>>(HttpStatus.NOT_FOUND);
        }
        TranscriptInfomation transcriptInfomation = transcriptInfomationService.findByClassroom(classroom);
        List<RubricImportantDto> rubricImportantDtos = new ArrayList<>();
        for (RubricImportant ri : transcriptInfomation.getRubricImportantList()) {
            rubricImportantDtos.add(new RubricImportantDto(ri));
        }
        if (rubricImportantDtos.size() == 0) {
            return new ResponseEntity<List<RubricImportantDto>>(new ArrayList<>(), HttpStatus.OK);
        }
        return new ResponseEntity<List<RubricImportantDto>>(rubricImportantDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/classroom/addRubrics/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ClassroomDto> addRubrics(@PathVariable("id") Long id, @RequestBody Map<String, String> parameters) {
        Classroom classroom = service.findById(id);
        if (classroom == null) {
            return new ResponseEntity<ClassroomDto>(HttpStatus.NOT_FOUND);
        }
        TranscriptInfomation transcriptInfomation = transcriptInfomationService.findByClassroom(classroom);
        TranscriptData transcriptData = transcriptDataService.findByClassroom(classroom);
        List<StudentTotalRubricPoint> studentTotalRubricPoints = transcriptData.getStudentTotalRubricPoints();
        List<RubricImportant> classroomRubricImportantList = transcriptInfomation.getRubricImportantList();
        if (classroomRubricImportantList == null) {
            classroomRubricImportantList = new ArrayList<>();
        }
        String rubricIdList = parameters.get("rubricIdList");
        String[] rubricIdArray = rubricIdList.split(",");
        String importantId = parameters.get("importantIdList");
        String[] importantIdArray = importantId.split(",");
        for (int i = 0; i < rubricIdArray.length; i++) {
            String rubricId = rubricIdArray[i];
            Rubric rubric = rubricService.findById(Long.parseLong(rubricId));
            if (rubric == null) {
                continue;
            }
            double important = Double.parseDouble(importantIdArray[i]);
            RubricImportant rubricImportant = rubricImportantService.findByRubricAndImportant(rubric, important);
            if (rubricImportant == null) {
                rubricImportant = new RubricImportant(rubric, Double.parseDouble(importantIdArray[i]), classroom.getId());
                rubricImportantService.save(rubricImportant);
            }
            if (!classroomRubricImportantList.contains(rubricImportant)) {
                rubricImportantService.save(rubricImportant);
                for (User student : classroom.getStudents()) {
                    StudentRubricPoint studentRubricPoint = new StudentRubricPoint(student, rubricImportant, 0);
                    studentRubricPointService.save(studentRubricPoint);
                    for (StudentTotalRubricPoint strp : studentTotalRubricPoints) {
                        if (strp.getStudentId().equals(student.getId())) {
                            List<StudentRubricPoint> studentRubricPoints = strp.getStudentRubricPoint();
                            studentRubricPoints.add(studentRubricPoint);
                            strp.setStudentRubricPoint(studentRubricPoints);
                            strp = studentTotalRubricPointService.recalculator(strp);
                            studentTotalRubricPointService.save(strp);
                        }
                    }
                }
                classroomRubricImportantList.add(rubricImportant);
            }
        }
        transcriptInfomation.setRubricImportantList(classroomRubricImportantList);
        transcriptInfomationService.save(transcriptInfomation);
        transcriptData.setTranscriptInnfomation(transcriptInfomation);
        transcriptDataService.save(transcriptData);
        service.save(classroom);
        return new ResponseEntity<ClassroomDto>(new ClassroomDto(classroom), HttpStatus.OK);
    }

    @RequestMapping(value = "/classroom/deleteRubrics/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ClassroomDto> deleteRubrics(@PathVariable("id") Long id, @RequestBody Map<String, String> parameters) {
        Classroom classroom = service.findById(id);
        if (classroom == null) {
            return new ResponseEntity<ClassroomDto>(HttpStatus.NOT_FOUND);
        }
        TranscriptInfomation transcriptInfomation = transcriptInfomationService.findByClassroom(classroom);
        TranscriptData transcriptData = transcriptDataService.findByClassroom(classroom);
        List<StudentTotalRubricPoint> studentTotalRubricPoints = transcriptData.getStudentTotalRubricPoints();
        List<RubricImportant> classroomRubricImportantList = transcriptInfomation.getRubricImportantList();

        String rubricImportantIdList = parameters.get("rubricImportantIdList");
        String[] rubricImportantArray = rubricImportantIdList.split(",");
        for (String rubricImportantId : rubricImportantArray) {
            RubricImportant rubricImportant;
            try {
                rubricImportant = rubricImportantService.findById(Long.parseLong(rubricImportantId));
                System.out.println(rubricImportant.getRubric().getName());
            } catch (Exception e) {
                System.out.println("rubricImportantId: " + rubricImportantId);
                System.out.println(e.getMessage());
                continue;
            }
            if (!rubricImportant.getClassroomId().equals(id)) {
                System.out.println("rubircImportantClassId !== id");
                continue;
            }
            for (StudentTotalRubricPoint strp : studentTotalRubricPoints) {
                for (int j = 0; j < strp.getStudentRubricPoint().size(); j++) {
                    StudentRubricPoint srp = strp.getStudentRubricPoint().get(j);
                    if (srp.getRubricImportant().getId().equals(rubricImportant.getId())) {
                        List<StudentRubricPoint> newSrp = strp.getStudentRubricPoint();
                        if (newSrp.size() == 1) {
                            newSrp = new ArrayList<>();
                        } else {
                            newSrp.remove(srp);
                        }
                        System.out.println("newSrp: " + newSrp);
                        strp.setStudentRubricPoint(newSrp);
                        strp = studentTotalRubricPointService.recalculator(strp);
                        studentTotalRubricPointService.save(strp);
                    }
                }
            }
            classroomRubricImportantList.removeIf(ri -> ri.getId().equals(rubricImportant.getId()));
            transcriptInfomation.setRubricImportantList(classroomRubricImportantList);
            transcriptInfomationService.save(transcriptInfomation);
        }
        return new ResponseEntity<ClassroomDto>(new ClassroomDto(classroom), HttpStatus.OK);
    }

    @RequestMapping(value = "/classroom/getData/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClassroomDataDto> getData(@PathVariable("id") Long id) {
        Classroom classroom = service.findById(id);
        if (classroom == null) {
            return new ResponseEntity<ClassroomDataDto>(HttpStatus.NOT_FOUND);
        }
        TranscriptData transcriptData = transcriptDataService.findByClassroom(classroom);
        return new ResponseEntity<ClassroomDataDto>(new ClassroomDataDto(classroom, transcriptData), HttpStatus.OK);
    }

    @RequestMapping(value = "/classroom/getData/detail/{classId}/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<List<StudentTotalRubricPointDto>> getDataDetail(@PathVariable("classId") Long classId, @PathVariable("studentId") Long studentId) {
        Classroom classroom = service.findById(classId);
        if (classroom == null) {
            return new ResponseEntity<List<StudentTotalRubricPointDto>>(HttpStatus.NOT_FOUND);
        }
        TranscriptData transcriptData = transcriptDataService.findByClassroom(classroom);
        List<StudentTotalRubricPointDto> studentTotalRubricPointDtos = new ArrayList<>();
        for (StudentTotalRubricPoint strp : transcriptData.getStudentTotalRubricPoints()) {
            if (strp.getStudentId().equals(studentId)) {
                studentTotalRubricPointDtos.add(new StudentTotalRubricPointDto(strp));
            }
        }
        return new ResponseEntity<List<StudentTotalRubricPointDto>>(studentTotalRubricPointDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/classroom/updatePoints/{classId}", method = RequestMethod.PATCH)
    public ResponseEntity<Double> updatePoints(@PathVariable("classId") Long classroomId, @RequestBody List<UpdatePointRequest> datas) {
        Classroom classroom = service.findById(classroomId);
        if (classroom == null) {
            return new ResponseEntity<Double>(HttpStatus.NOT_FOUND);
        }
        for (UpdatePointRequest data : datas) {
            Long studentId = data.getStudentId();
            Long rubricImportantId = data.getRubricImportantId();
            double newPoint = data.getPoint();
            TranscriptData transcriptData = transcriptDataService.findByClassroom(classroom);
            for (StudentTotalRubricPoint strp : transcriptData.getStudentTotalRubricPoints()) {
                if (strp.getStudentId().equals(studentId)) {
                    double avgPoint;
                    double totalImportantPoint = 0;
                    double totalPoint = 0;
                    for (StudentRubricPoint srp : strp.getStudentRubricPoint()) {
                        totalImportantPoint += srp.getRubricImportant().getImportant();
                        if (srp.getRubricImportant().getId().equals(rubricImportantId)) {
                            srp.setPoint(newPoint);
                            studentRubricPointService.save(srp);
                            totalPoint += newPoint * srp.getRubricImportant().getImportant();
                        } else {
                            totalPoint += srp.getPoint() * srp.getRubricImportant().getImportant();
                        }
                    }
                    avgPoint = totalPoint / totalImportantPoint;
                    if (avgPoint > 4) {
                        strp.setPassed(true);
                    }
                    strp.setTotalPoint(avgPoint);
                    studentTotalRubricPointService.save(strp);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/classroom/updateImportant/{classId}", method = RequestMethod.PATCH)
    public ResponseEntity<RubricImportantDto> updateImportant(@PathVariable("classId") Long classroomId, @RequestBody UpdateImportantRequest request) {
        Classroom classroom = service.findById(classroomId);
        if (classroom == null) {
            return new ResponseEntity<RubricImportantDto>(HttpStatus.NOT_FOUND);
        }
        RubricImportant rubricImportant;
        try {
            rubricImportant = rubricImportantService.findById(request.getRubricImportantId());
        } catch (Exception e) {
            return new ResponseEntity<RubricImportantDto>(HttpStatus.NOT_FOUND);
        }
        if (rubricImportant.getClassroomId().equals(classroomId)) {
            rubricImportant.setImportant(request.getNewImportant());
            rubricImportantService.save(rubricImportant);
            TranscriptData transcriptData = transcriptDataService.findByClassroom(classroom);
            for (StudentTotalRubricPoint strp : transcriptData.getStudentTotalRubricPoints()) {
                strp = studentTotalRubricPointService.recalculator(strp);
                studentTotalRubricPointService.save(strp);
            }
            transcriptDataService.save(transcriptData);
        } else {
            return new ResponseEntity<RubricImportantDto>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RubricImportantDto>(new RubricImportantDto(rubricImportant), HttpStatus.OK);
    }

    @RequestMapping(value = "/classroom/updateImportants/{classId}", method = RequestMethod.PATCH)
    public ResponseEntity<RubricImportantDto> updateImportants(@PathVariable("classId") Long classroomId, @RequestBody List<UpdateImportantRequest> requests) {
        Classroom classroom = service.findById(classroomId);
        if (classroom == null) {
            return new ResponseEntity<RubricImportantDto>(HttpStatus.NOT_FOUND);
        }
        for (UpdateImportantRequest request : requests) {
            RubricImportant rubricImportant;
            try {
                rubricImportant = rubricImportantService.findById(request.getRubricImportantId());
            } catch (Exception e) {
                return new ResponseEntity<RubricImportantDto>(HttpStatus.NOT_FOUND);
            }
            if (rubricImportant.getClassroomId().equals(classroomId)) {
                rubricImportant.setImportant(request.getNewImportant());
                rubricImportantService.save(rubricImportant);
                TranscriptData transcriptData = transcriptDataService.findByClassroom(classroom);
                for (StudentTotalRubricPoint strp : transcriptData.getStudentTotalRubricPoints()) {
                    strp = studentTotalRubricPointService.recalculator(strp);
                    studentTotalRubricPointService.save(strp);
                }
                transcriptDataService.save(transcriptData);
            } else {
                return new ResponseEntity<RubricImportantDto>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<RubricImportantDto>(HttpStatus.OK);
    }
}
