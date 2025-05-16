package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdresseRepository {

    @Autowired
    JdbcTemplate template;

    public List<Adresse> selectAll() {
        String sql = "SELECT * FROM adresse";
        RowMapper<Adresse> rowMapper = new BeanPropertyRowMapper<>(Adresse.class);
        return template.query(sql,rowMapper);
    }
}
