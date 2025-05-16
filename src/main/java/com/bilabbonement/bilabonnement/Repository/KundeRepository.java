package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.Kunde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KundeRepository {

    @Autowired
    JdbcTemplate template;

    public List<Kunde> selectLejerID() {
        String sql = "SELECT lejer_id, navn, email\n" +
                "FROM kunde";
        RowMapper<Kunde> rowMapper = new BeanPropertyRowMapper<>(Kunde.class);
        return template.query(sql, rowMapper);
    }
}
