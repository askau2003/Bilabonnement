package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.Status;
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

    public List<Status> antalIStatus() {
        // vælger status og tæller det antal gange den status forekommer.
        String sql = "SELECT status, COUNT(status) AS antal FROM bil GROUP BY status";
        RowMapper<Status> rowMapper = new BeanPropertyRowMapper<>(status.class);
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