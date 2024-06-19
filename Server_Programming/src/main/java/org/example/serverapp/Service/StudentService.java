package org.example.serverapp.Service;

import org.example.serverapp.Repositories.StudentRepository;
import org.example.serverapp.Utils.pojos.CreateOrUpdateStudentPojo;
import org.example.serverapp.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public long saveStudent(CreateOrUpdateStudentPojo pojo) {
        return studentRepository.saveStudent(pojo);
    }

    public Student getStudentById(long id) {
        return studentRepository.getStudentById(id);
    }

    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }

    public void updateStudent(long id, CreateOrUpdateStudentPojo pojo) {
        studentRepository.updateStudent(id, pojo);
    }

    public void deleteStudentById(long id) {
        studentRepository.deleteStudentById(id);
    }

    public void deleteStudents() {
        studentRepository.deleteStudents();
    }
}
