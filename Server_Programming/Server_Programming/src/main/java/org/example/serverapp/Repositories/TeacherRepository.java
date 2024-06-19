package org.example.serverapp.Repositories;


import org.example.serverapp.Utils.pojos.CreateOrUpdateTeacherPojo;
import org.example.serverapp.entities.Teacher;
import org.example.serverapp.exception.MyDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Objects;

@Repository
public class TeacherRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Teacher> teacherRowMapper;

    @Autowired
    public TeacherRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.teacherRowMapper = (rs, rowNum) -> {
            long id = rs.getLong("id");
            String firstName = rs.getString("firstName");
            String surname = rs.getString("surname");
            String patronymic = rs.getString("patronymic");
            return new Teacher(id, firstName, surname, patronymic);
        };
    }

    public long saveTeacher(CreateOrUpdateTeacherPojo pojo) {
        String sql = "insert into Teacher (firstName, surname, patronymic) values (?, ?, ?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, pojo.getFirstName());
            preparedStatement.setString(2, pojo.getSurname());
            preparedStatement.setString(3, pojo.getPatronymic());
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
    }

    public Teacher getTeacherById(long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from Teacher where id = ?",
                    new Object[]{id},
                    new int[]{Types.BIGINT},
                    teacherRowMapper);
        } catch (DataAccessException e) {
            throw new MyDataAccessException("Teacher getting by id error");
        }
    }

    public List<Teacher> getTeachers() {
        return jdbcTemplate.query("select * from Teacher", teacherRowMapper, new Object[]{});
    }

    public void updateTeacher(long id, CreateOrUpdateTeacherPojo pojo) {
        jdbcTemplate.update(
                "update Teacher set firstName = ?, surname = ?, patronymic = ? where id = ?",
                new Object[]{pojo.getFirstName(), pojo.getSurname(), pojo.getPatronymic(), id},
                new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT}
        );
    }

    public void deleteTeacherById(long id) {
        jdbcTemplate.update(
                "delete from Teacher where id = ?",
                new Object[]{id},
                new int[]{Types.BIGINT}
        );
    }

    public void deleteTeacher() {
        jdbcTemplate.update(
                "delete from Teacher"
        );
    }
}
