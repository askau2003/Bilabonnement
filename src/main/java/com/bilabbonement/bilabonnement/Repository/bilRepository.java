package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.bil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class bilRepository {
    @Autowired
    JdbcTemplate template;

    public List<bil> selectAll() {
        String sql = "SELECT * FROM bil";
        RowMapper<bil> rowMapper = new BeanPropertyRowMapper<>(bil.class);
        return template.query(sql, rowMapper);
    }
}
