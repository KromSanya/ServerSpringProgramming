package org.example.serverapp.Repositories;

import org.example.serverapp.Utils.pojos.CreateOrUpdateStudentPojo;

import org.example.serverapp.Utils.rowMappers.StudentRowMapper;
import org.example.serverapp.entities.Student;
import org.example.serverapp.exception.MyDataAccessException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Objects;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;
    private final StudentRowMapper studentRowMapper;

    @Autowired
    public StudentRepository(JdbcTemplate jdbcTemplate, StudentRowMapper studentRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.studentRowMapper = studentRowMapper;
    }

    public Long saveStudent(CreateOrUpdateStudentPojo pojo) {
        String sql = "insert into student (firstName, surname, patronymic, status, group_id) values (?, ?, ?, ?, ?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, pojo.getFirstName());
            preparedStatement.setString(2, pojo.getSurname());
            preparedStatement.setString(3, pojo.getPatronymic());
            preparedStatement.setString(4, pojo.getStatus());
            preparedStatement.setLong(5, pojo.getGroup_id());
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
    }

    public Student getStudentById(long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from student where id = ?",
                    new Object[]{id},
                    new int[]{Types.BIGINT},
                    studentRowMapper);
        } catch (DataAccessException e) {
            throw new MyDataAccessException("Student getting by id error");
        }
    }

    public List<Student> getStudents() {
        return jdbcTemplate.query("select * from student", studentRowMapper, new Object[]{});
    }

    public void updateStudent(long id, CreateOrUpdateStudentPojo pojo) {
        jdbcTemplate.update(
                "update student set firstName = ?, surname = ?, patronymic = ?, status = ?, group_id = ? where id = ?",
                new Object[]{pojo.getFirstName(), pojo.getSurname(), pojo.getPatronymic(), String.valueOf(pojo.getStatus()), pojo.getGroup_id(), id},
                new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.BIGINT}
        );
    }

    public void deleteStudentById(long id) {
        jdbcTemplate.update(
                "delete from student where id = ?",
                new Object[]{id},
                new int[]{Types.BIGINT}
        );
    }

    public void deleteStudents() {
        jdbcTemplate.update(
                "delete from student"
        );
    }
}

