package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.Bil;
import com.bilabbonement.bilabonnement.Model.Bynavn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BynavnRepository {
    @Autowired
    JdbcTemplate template;

    public List<Bynavn> selectAll() {
        String sql = "SELECT * FROM bynavn";
        RowMapper<Bynavn> rowMapper = new BeanPropertyRowMapper<>(Bynavn.class);
        return template.query(sql, rowMapper);
    }

    public void opretBy(Bynavn bynavn) {
        String sql = "INSERT INTO bynavn (postnummer, bynavn) VALUES (?, ?)";
        template.update(sql, bynavn.getPostnummer(), bynavn.getBynavn());
    }
}
