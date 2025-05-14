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

    public void insert(lejekontrakt kontrakt) {
        String sql = "INSERT INTO lejekontrakt " +
                "(startdato, slutdato, pris, depositum, valuta, oprettelsesdato, betalingsdato, afhentnings_adresse, afleverings_adresse, vognnummer, rapport_id, lejer_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        template.update(sql,
                kontrakt.getStartdato(),
                kontrakt.getSlutdato(),
                kontrakt.getPris(),
                kontrakt.getDepositum(),
                kontrakt.getValuta(),
                kontrakt.getOprettelsesdato(),
                kontrakt.getBetalingsdato(),
                kontrakt.getAfhentnings_adresse(),
                kontrakt.getAfleverings_adresse(),
                kontrakt.getVognnummer(),
                kontrakt.getRapport_id(),
                kontrakt.getLejer_id()
        );
    }
}
