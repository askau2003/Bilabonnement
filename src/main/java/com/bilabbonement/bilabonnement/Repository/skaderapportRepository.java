package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.skaderapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class skaderapportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<skaderapport> rowMapper = (rs, rowNum) -> {
        skaderapport rapport = new skaderapport();
        rapport.setRapport_id(rs.getInt("rapport_id"));
        rapport.setBeskrivelse(rs.getString("beskrivelse"));
        rapport.setPris(rs.getInt("pris"));
        rapport.setVognnummer(rs.getInt("vognnummer"));
        return rapport;
    };

    public List<skaderapport> findAll() {
        String sql = "SELECT * FROM skaderapport";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void save(skaderapport rapport) {
        String sql = "INSERT INTO skaderapport (beskrivelse, pris, vognnummer) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                rapport.getBeskrivelse(),
                rapport.getPris(),
                rapport.getVognnummer());
    }

    public skaderapport findById(int rapportId) {
        String sql = "SELECT * FROM skaderapport WHERE rapport_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, rapportId);
    }

    public void deleteById(int rapportId) {
        String sql = "DELETE FROM skaderapport WHERE rapport_id = ?";
        jdbcTemplate.update(sql, rapportId);
    }

    public void update(skaderapport rapport) {
        String sql = "UPDATE skaderapport SET beskrivelse = ?, pris = ?, vognnummer = ? WHERE rapport_id = ?";
        jdbcTemplate.update(sql,
                rapport.getBeskrivelse(),
                rapport.getPris(),
                rapport.getVognnummer(),
                rapport.getRapport_id());
    }
}