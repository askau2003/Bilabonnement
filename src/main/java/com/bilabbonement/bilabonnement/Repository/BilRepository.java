package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BilRepository {
    @Autowired
    JdbcTemplate template;

    public List<status> antalIStatus() {
        // vælger status og tæller det antal gange den status forekommer.
        String sql = "SELECT status, COUNT(status) AS antal FROM bil GROUP BY status";
        RowMapper<status> rowMapper = new BeanPropertyRowMapper<>(status.class);
        return template.query(sql, rowMapper);
    }
}
