package org.example.serverapp.Controllers;


import org.example.serverapp.Service.StudentService;
import org.example.serverapp.Utils.pojos.CreateOrUpdateStudentPojo;
import org.example.serverapp.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Long> saveStudent(@RequestBody CreateOrUpdateStudentPojo pojo) {
        long studentId = studentService.saveStudent(pojo);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getStudents();
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable long id, @RequestBody CreateOrUpdateStudentPojo pojo) {
        studentService.updateStudent(id, pojo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllStudents() {
        studentService.deleteStudents();
        return ResponseEntity.noContent().build();
    }
}
