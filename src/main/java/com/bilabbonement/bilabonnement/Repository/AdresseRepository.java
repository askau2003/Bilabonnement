package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.Adresse;
import com.bilabbonement.bilabonnement.Model.Forhaandsaftale;
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

    public void opretAdresse(Adresse adresse) {
        String sql = "INSERT INTO adresse " +
                "(vej_navn,vej_nummer,postnummer) " +
                "VALUES (?,?,?)";
        template.update(sql, adresse.getVej_navn(),adresse.getVej_nummer(),adresse.getPostnummer());
    }
}
