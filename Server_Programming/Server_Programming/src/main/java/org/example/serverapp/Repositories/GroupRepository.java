package org.example.serverapp.Repositories;

import org.example.serverapp.entities.StudentGroup;

import java.util.List;

import org.example.serverapp.exception.MyDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.Objects;


@Repository
public class GroupRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<StudentGroup> groupRowMapper;

    @Autowired
    public GroupRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.groupRowMapper = (rs, rowNum) -> new StudentGroup(rs.getLong("id"), rs.getString("name"));
    }

    public long saveGroup(String name) {
        String sql = "INSERT INTO MyGroup (name) VALUES (?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, name);
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public StudentGroup getGroupById(long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM MyGroup WHERE id = ?",
                    new Object[]{id},
                    new int[]{Types.BIGINT},
                    groupRowMapper
            );
        } catch (DataAccessException e) {
            throw new MyDataAccessException("Error retrieving group by id: " + id + e);
        }
    }

    public List<StudentGroup> getGroups() {
        return jdbcTemplate.query("SELECT * FROM MyGroup", groupRowMapper);
    }

    public void updateGroup(long id, String name) {
        jdbcTemplate.update(
                "UPDATE MyGroup SET name = ? WHERE id = ?",
                new Object[]{name, id},
                new int[]{Types.VARCHAR, Types.BIGINT}
        );
    }

    public void deleteGroupById(long id) {
        jdbcTemplate.update(
                "DELETE FROM MyGroup WHERE id = ?",
                new Object[]{id},
                new int[]{Types.BIGINT}
        );
    }

    public void deleteGroups() {
        jdbcTemplate.update("DELETE FROM MyGroup");
    }
}

