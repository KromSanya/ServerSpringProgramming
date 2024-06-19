package org.example.serverapp.Service;


import org.example.serverapp.Repositories.AttendanceRepository;
import org.example.serverapp.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public void saveStudentOnLessons(long studentId, List<Long> lessonIds) {
        attendanceRepository.saveStudentOnLessons(studentId, lessonIds);
    }

    public void saveLessonOnStudents(long lessonId, List<Long> studentsIds) {
        attendanceRepository.saveLessonOnStudents(lessonId, studentsIds);
    }

    public List<Student> getStudentsByLesson(long lessonId) {
        return attendanceRepository.getStudentsByLesson(lessonId);
    }
}
