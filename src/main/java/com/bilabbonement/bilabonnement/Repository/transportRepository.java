package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.transport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class transportRepository {
    @Autowired
    JdbcTemplate template;

    public List<transport> selectAll() {
        String sql = "SELECT * FROM transport";
        RowMapper<transport> rowMapper = new BeanPropertyRowMapper<>(transport.class);
        return template.query(sql, rowMapper);
    }

}
