package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.lejekontrakt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class lejekontraktRepository {
    @Autowired
    JdbcTemplate template;

    public List<lejekontrakt> selectAll() {
        String sql = "SELECT * FROM lejekontrakt";
        RowMapper<lejekontrakt> rowMapper = new BeanPropertyRowMapper<>(lejekontrakt.class);
        return template.query(sql, rowMapper);
    }
}
