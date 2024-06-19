package org.example.serverapp.Service;


import org.example.serverapp.Repositories.TeacherRepository;
import org.example.serverapp.Utils.pojos.CreateOrUpdateTeacherPojo;
import org.example.serverapp.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public long saveTeacher(CreateOrUpdateTeacherPojo pojo) {
        return teacherRepository.saveTeacher(pojo);
    }

    public Teacher getTeacherById(long id) {
        return teacherRepository.getTeacherById(id);
    }

    public List<Teacher> getTeachers() {
        return teacherRepository.getTeachers();
    }

    public void updateTeacher(long id, CreateOrUpdateTeacherPojo pojo) {
        teacherRepository.updateTeacher(id, pojo);
    }

    public void deleteTeacherById(long id) {
        teacherRepository.deleteTeacherById(id);
    }

    public void deleteTeachers() {
        teacherRepository.deleteTeacher();
    }
}
