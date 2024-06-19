package org.example.serverapp.Utils.rowMappers;



import org.example.serverapp.entities.Student;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        String firstName = rs.getString("firstName");
        String surname = rs.getString("surname");
        String patronymic = rs.getString("patronymic");
        String status = rs.getString("status");
        long groupId = rs.getLong("group_id");
        return new Student(id, firstName, surname, patronymic, status, groupId);
    }
}
