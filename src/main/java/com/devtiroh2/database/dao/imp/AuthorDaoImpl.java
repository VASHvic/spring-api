package com.devtiroh2.database.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.devtiroh2.database.dao.AuthorDao;
import com.devtiroh2.database.domain.Author;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcClient jdbcClient;

    // Done in 3 ways, jdbclient is the most modern one
    public AuthorDaoImpl(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            JdbcClient jdbcClient) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void create(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO authors (id, name, age) VALUES (:id, :name, :age)";
        jdbcClient.sql(sql)
                .param("id", author.getId())
                .param("name", author.getName())
                .param("age", author.getAge())
                .update();
    }

    @Override
    public Optional<Author> find(Long authorId) {
        List<Author> results = jdbcTemplate.query("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1",
                new AuthorRowMapper(), authorId);
        return results.stream().findFirst();
    }

    public static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }

    @Override
    public List<Author> findMany(List<Long> ids) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", ids);
        return namedParameterJdbcTemplate.query(
                "SELECT id, name, age FROM authors WHERE id IN (:ids)",
                parameters,
                new AuthorRowMapper());

    }
}
