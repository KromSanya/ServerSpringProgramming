package org.example.serverapp.Controllers;


import org.example.serverapp.Service.AttendanceService;
import org.example.serverapp.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
public class AttendanceController {

    @Autowired
    public AttendanceService attendanceService;

    @PostMapping("student/{id}/lessons")
    public ResponseEntity saveStudentOnLessons(@PathVariable("id") long studentId, @RequestBody List<Long> lessonIds) {
        attendanceService.saveStudentOnLessons(studentId, lessonIds);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("lesson/{id}/students")
    public ResponseEntity saveLessonOnStudents(@PathVariable("id") long lessonId, @RequestBody List<Long> studentIds) {
        attendanceService.saveLessonOnStudents(lessonId, studentIds);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("lesson/{id}/students")
    public ResponseEntity<List<Student>> getStudentsByLesson(@PathVariable("id") long lessonId) {
        return new ResponseEntity<>(attendanceService.getStudentsByLesson(lessonId), HttpStatus.OK);
    }
}
