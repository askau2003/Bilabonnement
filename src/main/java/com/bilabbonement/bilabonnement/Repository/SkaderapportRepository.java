package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.Skaderapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SkaderapportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Skaderapport> rowMapper = (rs, rowNum) -> {
        Skaderapport rapport = new Skaderapport();
        rapport.setRapport_id(rs.getInt("rapport_id"));
        rapport.setBeskrivelse(rs.getString("beskrivelse"));
        rapport.setPris(rs.getInt("pris"));
        rapport.setVognnummer(rs.getInt("vognnummer"));
        return rapport;
    };

    public List<Skaderapport> findAll() {
        String sql = "SELECT * FROM skaderapport";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void save(Skaderapport rapport) {
        String sql = "INSERT INTO skaderapport (beskrivelse, pris, vognnummer) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                rapport.getBeskrivelse(),
                rapport.getPris(),
                rapport.getVognnummer());
    }

    public Skaderapport findById(int rapportId) {
        String sql = "SELECT * FROM skaderapport WHERE rapport_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, rapportId);
    }

    public void deleteById(int rapportId) {
        String sql = "DELETE FROM skaderapport WHERE rapport_id = ?";
        jdbcTemplate.update(sql, rapportId);
    }

    public void update(Skaderapport rapport) {
        String sql = "UPDATE skaderapport SET beskrivelse = ?, pris = ?, vognnummer = ? WHERE rapport_id = ?";
        jdbcTemplate.update(sql,
                rapport.getBeskrivelse(),
                rapport.getPris(),
                rapport.getVognnummer(),
                rapport.getRapport_id());
    }
}